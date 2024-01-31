package cn.tedu.asset.admin.service;


import cn.tedu.asset.admin.pojo.dto.UserLoginDTO;
import cn.tedu.asset.admin.pojo.dto.UserUpdateDTO;
import cn.tedu.asset.admin.pojo.vo.UserVO;

public interface IUserService {

    UserVO login(UserLoginDTO userLoginDTO);
    void updateUser(UserUpdateDTO userUpdateDTO);
}
