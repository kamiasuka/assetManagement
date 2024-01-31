package cn.tedu.asset.admin.dao.persist.mapper;


import cn.tedu.asset.admin.pojo.entity.User;
import cn.tedu.asset.admin.pojo.vo.UserVO;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    UserVO selectByUsername(String username);
    int updateUser(User user);
}
