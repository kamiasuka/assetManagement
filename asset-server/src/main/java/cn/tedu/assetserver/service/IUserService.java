package cn.tedu.assetserver.service;

import cn.tedu.assetserver.pojo.dto.UserLoginDTO;
import cn.tedu.assetserver.pojo.dto.UserUpdateDTO;
import cn.tedu.assetserver.pojo.vo.UserVO;

public interface IUserService {

    UserVO login(UserLoginDTO userLoginDTO);
    void updateUser(UserUpdateDTO userUpdateDTO);
}
