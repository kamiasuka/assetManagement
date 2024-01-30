package cn.tedu.assetadmin.service.impl;

import cn.tedu.assetadmin.mapper.UserMapper;
import cn.tedu.assetadmin.pojo.dto.UserLoginDTO;
import cn.tedu.assetadmin.pojo.dto.UserUpdateDTO;
import cn.tedu.assetadmin.pojo.entity.User;
import cn.tedu.assetadmin.pojo.vo.UserVO;
import cn.tedu.assetadmin.service.IUserService;
import cn.tedu.assetcommom.ex.ServiceException;
import cn.tedu.assetcommom.response.StatusCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public UserVO login(UserLoginDTO userLoginDTO) {
        UserVO userVO = userMapper.selectByUsername(userLoginDTO.getUsername());
        if (userVO==null){
            throw new ServiceException(StatusCode.USERNAME_ERROR);
        }
        if (!userLoginDTO.getPassword().equals(userVO.getPassword())){
            throw new ServiceException(StatusCode.PASSWORD_ERROR);
        }
        return userVO;
    }

    @Override
    public void updateUser(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO,user);
        userMapper.updateUser(user);
    }
}
