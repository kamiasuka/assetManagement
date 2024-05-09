package cn.tedu.asset.manage.dao.cache.repository.impl;

import cn.tedu.asset.manage.Util.PageInfoToPageDataConverter;
import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import com.alibaba.fastjson2.JSON;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static cn.tedu.asset.manage.Util.AssetCacheConsts.*;

@Slf4j
@Repository
public class AssetCacheRepositoryImpl implements IAssetCacheRepository {
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired(required = false)
    private AssetMapper assetMapper;
    @Value("16")
    private Integer defaultQueryPageSize;


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
    public PageData<AssetVO> listAll(int pageNum) {
        log.debug("开始处理【查询资产数据】的缓存数据访问");
        PageHelper.startPage(pageNum,defaultQueryPageSize);
        Page<AssetVO> page = assetMapper.exportVo();
        PageInfo<AssetVO> pageInfo = new PageInfo<>(page);
        PageData<AssetVO> pageData =  PageInfoToPageDataConverter.convert(pageInfo);

        String key = "key_All_"+pageNum;
        ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
        String assetPOJson = JSON.toJSONString(pageData);
        opsForValue.set(key, assetPOJson,86400, TimeUnit.SECONDS);

        String json = opsForValue.get(key);
        PageData<AssetVO> data = JSON.toJavaObject(json,PageData.class);

        return data;
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
