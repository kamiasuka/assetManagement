package cn.tedu.asset.manage.dao.cache.repository.impl;

import cn.tedu.asset.manage.dao.cache.repository.IAssetInfoCacheRepository;
import cn.tedu.asset.manage.pojo.param.AssetInfoAddParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AssetInfoCacheRepositoryImpl implements IAssetInfoCacheRepository {

    @Autowired
//    private RedisTemplate<String, Serializable> redisTemplate;
    public AssetInfoCacheRepositoryImpl(){log.info("创建缓存存储库对象：AssetInfoCacheRepositoryImpl");}



    @Override
    public void addNew(AssetInfoAddParam assetInfoAddParam) {
        log.debug("开始处理【在缓存中新增资产信息】，资产信息：{}",assetInfoAddParam);

    }
}
