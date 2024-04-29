package cn.tedu.asset.manage.dao.cache.repository.impl;

import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import com.alibaba.fastjson2.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

import static cn.tedu.asset.manage.Util.AssetCacheConsts.*;

@Slf4j
@Repository
public class AssetCacheRepositoryImpl implements IAssetCacheRepository {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private AssetMapper assetMapper;

    @Override
    public void saveCategory(String type) {
        log.debug("开始处理【存储资产分类】的缓存预热,参数：" + type);
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        opsForList.leftPush(KEYS_ALL_CATEGORY, type);
    }

    @Override
    public void saveAsset(String type, List<AssetVO> assetVOList) {
        log.debug("开始处理【存储资产】的缓存预热,参数：" + assetVOList);
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        String assetPOJson = JSON.toJSONString(assetVOList);
        opsForList.rightPush(type, assetPOJson);

    }

    @Override
    public void saveAll() {
        List<AssetVO> voList = assetMapper.exportVo();
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        String assetPOJson = JSON.toJSONString(voList);
        opsForList.rightPush(KEY_ALL_ASSET, assetPOJson);

    }

    @Override
    public void deleteAll() {
        log.debug("开始处理【清理缓存】的业务");
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        List keys = opsForList.range(KEYS_ALL_CATEGORY, 0, -1);
        redisTemplate.delete(keys);
    }

    @Override
    public List<AssetVO> listAll() {
        log.debug("开始处理【查询资产数据】的缓存数据访问");

        return null;
    }

    @Override
    public List<PageData> listByAsset(String type) {
        log.debug("开始处理【根据type查询资产数据】的缓存数据访问，参数:" + type);
        return null;
    }


    @Override
    public void updateCache(AssetUpdatePO assetUpdatePO) {
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        String assetPOJson = JSON.toJSONString(assetUpdatePO);
        opsForSet.add(assetUpdatePO.getType(), assetPOJson);
    }


}
