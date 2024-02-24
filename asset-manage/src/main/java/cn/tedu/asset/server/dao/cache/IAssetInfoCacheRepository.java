package cn.tedu.asset.server.dao.cache;

import cn.tedu.asset.server.pojo.param.AssetInfoAddParam;

public interface IAssetInfoCacheRepository {
    /**
     * 在缓存中添加资产信息
     * @param assetInfoAddParam
     */
    void addNew(AssetInfoAddParam assetInfoAddParam);
}
