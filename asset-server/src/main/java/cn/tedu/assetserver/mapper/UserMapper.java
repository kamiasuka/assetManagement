package cn.tedu.assetserver.mapper;

import cn.tedu.assetserver.pojo.entity.User;
import cn.tedu.assetserver.pojo.vo.UserVO;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    UserVO selectByUsername(String username);
    int update(User user);
}
