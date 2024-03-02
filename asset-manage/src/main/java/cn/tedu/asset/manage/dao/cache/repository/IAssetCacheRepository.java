package cn.tedu.asset.manage.dao.cache.repository;

import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;

import java.io.Serializable;
import java.util.List;

public interface IAssetCacheRepository {
    void deleteAll();

    void save(String type);

    void saveByCategory(List<AssetPO> assetPOList);

    List<AssetVO> listByAsset(String type);

    void updateCache(AssetUpdatePO assetUpdatePO);
}
