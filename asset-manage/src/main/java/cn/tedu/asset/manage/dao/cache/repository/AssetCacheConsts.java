package cn.tedu.asset.manage.dao.cache.repository;

public interface AssetCacheConsts {
    /**
     * 缓存数据的KEY的前缀：根据父级存储的资产列表
     */
    String KEY_PREFIX_LIST_BY_PARENT = "asset:list-by-parent:";

    /**
     * 缓存数据的KEY的前缀：资产数据
     */
    String KEY_PREFIX_ITEM = "asset:item:";

    /**
     * 缓存中所有列表数据的Key集合的Key
     */
    String KEY_ALL_KEYS = "asset:keys";
}
