package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.param.AssetStatisticParam;
import cn.tedu.asset.manage.pojo.po.AssetCategoryPO;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.vo.AssetCategoryVO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.service.IAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AssetServiceImpl implements IAssetService {
    @Autowired
    private IAssetCacheRepository iAssetCacheRepository;
    @Autowired
    private AssetMapper assetMapper;

    @Override
    public List<AssetVO> getAssetByType(String type) {
        log.debug("开始处理【查询资产】的业务");
        return iAssetCacheRepository.listByAsset();
    }

    @Override
    public void rebuildCache() {
        log.debug("开始处理【重建资产数据】的业务");
        iAssetCacheRepository.deleteAll();

        List<String> categoryList = assetMapper.listAllCategory();
        List<AssetPO> assetPOList = null;

        for (String type: categoryList){
            iAssetCacheRepository.save(type);
            assetPOList = assetMapper.listAssetByCategory(type);
            iAssetCacheRepository.saveByCategory(assetPOList);
        }
    }

    @Override
    public List<AssetStatisticParam> getStatistics() {
        log.debug("开始处理【获取统计信息】的业务");
        List<AssetStatisticParam> list = new ArrayList<>();

        /**
         * 获取所有一级分类名称
         */
        List<String> types = assetMapper.listByLevel(1);
        log.debug("获取所有一级分类的名称types：{}",types);


        for (int i=0;i<types.size();i++){
            log.debug("开始封装每{}个分类的统计信息AssetStatisticParam",i+1);
            AssetStatisticParam asp = new AssetStatisticParam();
            asp.setType(types.get(i));
            /**
             * 获取该一级分类的资产总数
             */
            Integer num = assetMapper.numberSumQuery(types.get(i));
            asp.setNum(num);
            /**
             * 获取该一级分类的资产总值
             */
            Double worth = assetMapper.worthSumQuery(types.get(i));
            if (worth == null) worth = 0.0;
            log.debug("worth = {}",worth);
            asp.setWorth(worth);
            log.debug("封装完成：{}",asp);
            list.add(asp);
        }
        log.debug("封装完成：{}",list);

        return list;
    }
}
