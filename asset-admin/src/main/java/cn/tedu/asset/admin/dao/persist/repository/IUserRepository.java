package cn.tedu.asset.admin.dao.persist.repository;

import cn.tedu.asset.admin.pojo.entity.User;
import cn.tedu.asset.admin.pojo.vo.UserVO;

public interface IUserRepository {


    int updateById(User user);

}
