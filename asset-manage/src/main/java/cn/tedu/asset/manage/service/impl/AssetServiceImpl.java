package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.po.AssetCategoryPO;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.vo.AssetCategoryVO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.service.IAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
