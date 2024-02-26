package cn.tedu.asset.manage.dao.cache.repository.impl;

import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import static cn.tedu.asset.manage.dao.cache.repository.AssetCacheConsts.KEY_ALL_KEYS;

@Slf4j
@Repository
public class AssetCacheRepositoryImpl implements IAssetCacheRepository {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;

    @Override
    public void save(String type) {
        log.debug("开始处理【存储资产分类】的缓存数据,参数：" + type);
        //String key = KEY_PREFIX_ITEM+type;
        SetOperations<String, Serializable> setOperations = redisTemplate.opsForSet();
        setOperations.add(KEY_ALL_KEYS, type);
    }

    @Override
    public void saveByCategory(List<AssetPO> assetPOList) {
        log.debug("开始处理【存储资产】的缓存数据,参数：" + assetPOList);
        SetOperations<String, Serializable> setOperations = redisTemplate.opsForSet();
        for (AssetPO assetPO : assetPOList) {
            setOperations.add(assetPO.getType(), assetPO);
        }
    }

    @Override
    public void deleteAll() {
        log.debug("开始处理【删除所有的地区列表数据】的缓存数据访问，无参数");
        SetOperations<String, Serializable> opsForSet = redisTemplate.opsForSet();
        Set keys = opsForSet.members(KEY_ALL_KEYS);
        redisTemplate.delete(keys);
    }

    @Override
    public List<AssetVO> listByAsset() {
        return null;
    }
}
