package cn.tedu.asset.admin.service;


import cn.tedu.asset.admin.pojo.dto.UserInfoUpdateDTO;
import cn.tedu.asset.admin.pojo.dto.UserLoginDTO;
import cn.tedu.asset.admin.pojo.vo.LoginResultVO;
import cn.tedu.asset.admin.pojo.vo.UserVO;

public interface IUserService {

    LoginResultVO login(UserLoginDTO userLoginDTO);

    void updateInfo(Long id, UserInfoUpdateDTO userInfoUpdateDTO);

    void updatePassword(Long id, String newPassword);


    UserVO getInfoById(Long id);
}
