package cn.tedu.asset.manage.dao.cache.repository.impl;

import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static cn.tedu.asset.manage.dao.cache.repository.AssetCacheConsts.KEY_ALL_KEYS;

@Slf4j
@Repository
public class AssetCacheRepositoryImpl implements IAssetCacheRepository {
    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    private IAssetCacheRepository iAssetCacheRepository;
    @Autowired
    private AssetMapper assetMapper;

    @Override
    public void save(String type) {
        log.debug("开始处理【存储资产分类】的缓存预热,参数：" + type);
        ListOperations<String, Serializable> listOperations = redisTemplate.opsForList();
        listOperations.leftPush(KEY_ALL_KEYS, type);
    }

    @Override
    public void saveByCategory(List<AssetPO> assetPOList) {
        log.debug("开始处理【存储资产】的缓存预热,参数：" + assetPOList);
        ListOperations<String, Serializable> listOperations = redisTemplate.opsForList();
        for (AssetPO assetPO : assetPOList) {
            listOperations.leftPush(assetPO.getType(), assetPO);
        }
    }

    @Override
    public void deleteAll() {
        log.debug("开始处理【删除所有的地区列表数据】的缓存预热，无参数");
        ListOperations<String, Serializable> opsForList = redisTemplate.opsForList();
        List keys = opsForList.range(KEY_ALL_KEYS, 0, -1);
        redisTemplate.delete(keys);
    }

    @Override
    public List<AssetVO> listByAsset(String type) {
        log.debug("开始处理【根据type查询资产数据】的缓存数据访问，参数:"+type);
        ListOperations<String,Serializable> listOperations = redisTemplate.opsForList();
        List assetPOList = listOperations.range(type,0,-1);

        /** 缓存未命中 */
        if (assetPOList.isEmpty()){
            List<AssetPO> poList = assetMapper.listAssetByCategory(type);

            AssetVO assetVO = new AssetVO();
            List<AssetVO> voList = new ArrayList<>();
            for (AssetPO assetPO : poList ){
                BeanUtils.copyProperties(assetPO,assetVO);
                voList.add(assetVO);
                listOperations.leftPush(assetPO.getType(), type);
            }
            return voList;
        }
        return assetPOList;
    }
}
