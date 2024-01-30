package cn.tedu.assetadmin.dao.persist.mapper;


import cn.tedu.assetadmin.pojo.entity.User;
import cn.tedu.assetadmin.pojo.vo.UserVO;

public interface UserMapper {
    UserVO selectByUsername(String username);
    int updateUser(User user);
}
