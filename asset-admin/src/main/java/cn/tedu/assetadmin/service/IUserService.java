package cn.tedu.assetadmin.service;


import cn.tedu.assetadmin.pojo.dto.UserLoginDTO;
import cn.tedu.assetadmin.pojo.dto.UserUpdateDTO;
import cn.tedu.assetadmin.pojo.vo.UserVO;

public interface IUserService {

    UserVO login(UserLoginDTO userLoginDTO);
    void updateUser(UserUpdateDTO userUpdateDTO);
}
