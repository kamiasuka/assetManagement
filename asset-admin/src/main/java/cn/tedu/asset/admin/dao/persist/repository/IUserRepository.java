package cn.tedu.asset.admin.dao.persist.repository;

import cn.tedu.asset.admin.pojo.po.UserPO;

public interface IUserRepository {


    int updateById(UserPO userPO);
}
