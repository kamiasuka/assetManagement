package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import cn.tedu.asset.manage.Util.PageInfoToPageDataConverter;
import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.dto.AssetStatisticDTO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import cn.tedu.asset.manage.service.IAssetService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class AssetServiceImpl implements IAssetService {
    @Value("5")
    private Integer defaultQueryPageSize;
    @Autowired
    private IAssetCacheRepository iAssetCacheRepository;
    @Autowired
    private AssetMapper assetMapper;

    @Override
    public PageData<AssetVO> getAssetByType(String type,Integer pageNum) {
        log.debug("开始处理【根据分类加载资产】的业务，类别参数：{}，页码：{}",type,pageNum);
        PageHelper.startPage(pageNum, 4);
        Page<AssetVO> voList = assetMapper.pageListByAsset(type);
        PageInfo<AssetVO> pageInfo = new PageInfo<>(voList);
        System.out.println(pageInfo+" size:===="+pageInfo.getPageSize());
        return PageInfoToPageDataConverter.convert(pageInfo);
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
    public List<AssetStatisticDTO> getStatistics() {
        log.debug("开始处理【获取统计信息】的业务");
        List<AssetStatisticDTO> list = new ArrayList<>();

        /**
         * 获取所有一级分类名称
         */
        List<String> types = assetMapper.listByLevel(1);
        log.debug("获取所有一级分类的名称types：{}",types);

        for (int i=0;i<types.size();i++){
            log.debug("开始封装第{}个分类的统计信息AssetStatisticDTO",i+1);
            AssetStatisticDTO asp = new AssetStatisticDTO();
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

    @Override
    public AssetVO searchAsset(String keyword) {
        log.debug("开始处理【资产搜索】的请求,关键词：{}",keyword);

        return null;
    }

    @Override
    public void addNew(AssetAddDTO assetAddDTO) {
        log.debug("开始处理【资产录入】的业务");

    }

    @Override
    public void assetUpdate(AssetUpdateDTO assetUpdateDTO) {
        log.debug("开始处理【资产变更】的业务，参数：{}",assetUpdateDTO);
        AssetUpdatePO assetUpdatePO = new AssetUpdatePO();
        BeanUtils.copyProperties(assetUpdateDTO,assetUpdatePO);
        assetUpdatePO.setReviewStatus("审核中");

        iAssetCacheRepository.updateCache(assetUpdatePO);

        int num = assetMapper.assetUpdate(assetUpdatePO);
        if (num != 1){
            throw new ServiceException(StatusCode.OPERATION_FAILED,"资产变更失败！");
        }

    }
}
