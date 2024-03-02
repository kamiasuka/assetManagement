package cn.tedu.asset.commom.consts.cache;

public interface PassportCacheConsts {
    /**
     * 缓存数据的KEY的前缀：用户状态数据
     */
    String KEY_PREFIX_USER_STATE = "passport:user-state:";

    /**
     * 用户状态数据在Redis中的hash对象中“启用状态”的Key
     */
    String HASH_KEY_USER_ENABLE = "enable";
}
