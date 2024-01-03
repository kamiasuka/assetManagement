package cn.tedu.assetserver.service;

import cn.tedu.assetserver.pojo.dto.UserLoginDTO;
import cn.tedu.assetserver.pojo.dto.UserRegDTO;
import cn.tedu.assetserver.pojo.dto.UserUpdateDTO;
import cn.tedu.assetserver.pojo.vo.UserAdminVO;
import cn.tedu.assetserver.pojo.vo.UserVO;

import java.util.List;

public interface IUserService {
    void reg(UserRegDTO userRegDTO);

    UserVO login(UserLoginDTO userLoginDTO);

    void update(UserUpdateDTO userUpdateDTO);

    List<UserAdminVO> list();

    void deleteById(Long id);
}
