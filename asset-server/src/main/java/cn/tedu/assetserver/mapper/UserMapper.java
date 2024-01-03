package cn.tedu.assetserver.mapper;

import cn.tedu.assetserver.pojo.entity.User;
import cn.tedu.assetserver.pojo.vo.UserAdminVO;
import cn.tedu.assetserver.pojo.vo.UserVO;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    UserVO selectByUsername(String username);

    int insert(User user);

    int update(User user);

    List<UserAdminVO> select();

    String selectUrlById(Long id);

    int deleteById(Long id);
}
