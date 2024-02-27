package cn.tedu.asset.manage.dao.cache.repository;

import cn.tedu.asset.manage.pojo.param.AssetInfoAddParam;

public interface IAssetInfoCacheRepository {
    /**
     * 在缓存中添加资产信息
     * @param assetInfoAddParam
     */
    void addNew(AssetInfoAddParam assetInfoAddParam);
}
