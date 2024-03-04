package com.tedu.asset.passport.dao.persist.mapper;

import com.tedu.asset.passport.pojo.vo.UserLoginInfoVO;

public interface UserMapper {
    /**
     * 根据用户名查询用户的登录信息
     *
     * @param username 用户名
     * @return 匹配的用户的登录信息，如果没有匹配的数据，则返回null
     */
    UserLoginInfoVO getLoginInfoByUsername(String username);
}
