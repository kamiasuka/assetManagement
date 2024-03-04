package com.tedu.asset.passport.dao.persist.repository.impl;

import com.tedu.asset.passport.dao.persist.mapper.UserMapper;
import com.tedu.asset.passport.dao.persist.repository.IUserRepository;
import com.tedu.asset.passport.pojo.vo.UserLoginInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {

    @Autowired
    private UserMapper userMapper;

    public UserRepositoryImpl() {
        log.info("创建存储库对象：UserRepositoryImpl");
    }
    @Override
    public UserLoginInfoVO getLoginInfoByUsername(String username) {
        log.debug("开始执行【根据用户名查询用户登录信息】的数据访问，参数：{}", username);
        return userMapper.getLoginInfoByUsername(username);
    }
}
