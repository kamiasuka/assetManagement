package cn.tedu.asset.server.dao.cache.impl;

import cn.tedu.asset.server.dao.cache.IAssetInfoCacheRepository;
import cn.tedu.asset.server.pojo.param.AssetInfoAddParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

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
