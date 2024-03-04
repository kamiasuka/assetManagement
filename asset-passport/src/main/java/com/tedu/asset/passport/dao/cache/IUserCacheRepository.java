package com.tedu.asset.passport.dao.cache;


import cn.tedu.asset.commom.consts.cache.PassportCacheConsts;
import cn.tedu.asset.commom.pojo.po.UserCachePO;

/**
 * 处理用户缓存数据的存储库接口
 *
 * @author java@tedu.cn
 * @version 3.0
 */
public interface IUserCacheRepository extends PassportCacheConsts {

    /**
     * 向缓存中写入用户登录信息
     *
     * @param userId      用户ID
     * @param userCachePO 用户登录信息
     */
    void saveUserState(Long userId, UserCachePO userCachePO);

    /**
     * 从缓存中删除用户登录信息
     *
     * @param userId 用户ID
     * @return 如果删除成功，将返回true，否则，将返回false
     */
    boolean deleteUserState(Long userId);

    /**
     * 续期用户登录信息
     *
     * @param userId 用户ID
     */
    void renewal(Long userId);

    /**
     * 从缓存中读取用户登录信息
     *
     * @param userId 用户ID
     * @return 匹配的用户信息，如果没有匹配的数据，则返回null
     */
    UserCachePO getUserState(Long userId);

}
