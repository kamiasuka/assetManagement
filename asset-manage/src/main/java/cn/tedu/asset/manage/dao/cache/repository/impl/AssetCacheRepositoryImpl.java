package cn.tedu.asset.manage.dao.cache.repository.impl;

import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static cn.tedu.asset.manage.Util.AssetCacheConsts.ALL_ASSET;
import static cn.tedu.asset.manage.Util.AssetCacheConsts.KEY_ALL_KEYS;

@Slf4j
@Repository
public class AssetCacheRepositoryImpl implements IAssetCacheRepository {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private AssetMapper assetMapper;

    @Override
    public void save(String type) {
        log.debug("开始处理【存储资产分类】的缓存预热,参数：" + type);
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        opsForList.leftPush(KEY_ALL_KEYS, type);
    }

    @Override
    public void saveByCategory(List<AssetPO> assetPOList) {
        log.debug("开始处理【存储资产】的缓存预热,参数：" + assetPOList);
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        for (AssetPO assetPO : assetPOList) {
            String assetPOJson = JSON.toJSONString(assetPO);
            opsForList.rightPush(assetPO.getType(), assetPOJson);
        }

    }

    @Override
    public void saveAll() {
        List<AssetVO> voList = assetMapper.exportVo();
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        String assetPOJson = JSON.toJSONString(voList);
        opsForList.rightPush(ALL_ASSET, assetPOJson);

    }

    @Override
    public void deleteAll() {
        log.debug("开始处理【清理缓存】的业务");
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        List keys = opsForList.range(KEY_ALL_KEYS,0,-1);
        redisTemplate.delete(keys);

    }

    @Override
    public List<AssetVO> listAll() {
        //?缓存里是根据资产分类存的数据，把所有资产数据装进一个list里返回？

        log.debug("开始处理【查询资产数据】的缓存数据访问" );
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        List<String> assetJsonSet = opsForList.range(ALL_ASSET,0,-1);

        List<AssetVO> voList = new ArrayList<>();
        for (String assetPOJson : assetJsonSet) {
            AssetVO assetVO = JSON.toJavaObject(JSON.parseObject(assetPOJson), AssetVO.class);
            voList.add(assetVO);
        }
        return voList;
    }

    @Override
    public List<AssetVO> listByAsset(String type) {
        log.debug("开始处理【根据type查询资产数据】的缓存数据访问，参数:" + type);
        ListOperations<String, String> opsForList = redisTemplate.opsForList();
        List<String> assetJsonSet = opsForList.range(type,0,-1);

        /** 缓存未命中 */
        if (assetJsonSet.isEmpty()) {
            log.debug("缓存未命中，访问数据库，参数:" + type);
            List<AssetPO> poList = assetMapper.listAssetByCategory(type);
            List<AssetVO> voList = new ArrayList<>();
            for (AssetPO assetPO : poList) {
                AssetVO assetVO = new AssetVO();
                BeanUtils.copyProperties(assetPO, assetVO);
                voList.add(assetVO);
                /** 更新缓存 */
                String assetPOJson = JSON.toJSONString(assetPO);
                opsForList.leftPush(assetPO.getType(), assetPOJson);
            }
            return voList;
        }

        List<AssetVO> voList = new ArrayList<>();
        for (String assetPOJson : assetJsonSet) {
            AssetVO assetVO = JSON.toJavaObject(JSON.parseObject(assetPOJson), AssetVO.class);
            voList.add(assetVO);
        }
        return voList;
    }


    @Override
    public void updateCache(AssetUpdatePO assetUpdatePO) {
        SetOperations<String, String> opsForSet = redisTemplate.opsForSet();
        String assetPOJson = JSON.toJSONString(assetUpdatePO);
        opsForSet.add(assetUpdatePO.getType(), assetPOJson);
    }




}
