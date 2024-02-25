package cn.tedu.asset.manage.dao.cache.repository;

import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;

import java.util.List;

public interface IAssetCacheRepository {
    void deleteAll();

    List<AssetVO> listByAsset();

    void save(String type);

    void saveByCategory(AssetPO assetPO);

}
