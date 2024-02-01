package cn.tedu.asset.admin.dao.persist.repository.impl;

import cn.tedu.asset.admin.dao.persist.mapper.UserMapper;
import cn.tedu.asset.admin.dao.persist.repository.IUserRepository;
import cn.tedu.asset.admin.pojo.po.UserPO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class UserRepositoryImpl implements IUserRepository {
    @Autowired
    private UserMapper userMapper;
    @Override
    public int updateById(UserPO userPO) {
        log.debug("开始执行【更新用户】的数据访问，参数：{}", userPO);
        return userMapper.updateById(userPO);
    }
}
