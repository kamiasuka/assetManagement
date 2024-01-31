package cn.tedu.asset.admin.service.impl;

import cn.tedu.asset.admin.dao.persist.mapper.UserMapper;
import cn.tedu.asset.admin.pojo.dto.UserLoginDTO;
import cn.tedu.asset.admin.pojo.dto.UserUpdateDTO;
import cn.tedu.asset.admin.pojo.entity.User;
import cn.tedu.asset.admin.pojo.vo.UserVO;
import cn.tedu.asset.admin.service.IUserService;
import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    UserMapper userMapper;

    @Override
    public UserVO login(UserLoginDTO userLoginDTO) {
        UserVO userVO = userMapper.selectByUsername(userLoginDTO.getUsername());
        if (userVO==null){
            System.out.println(("用户名并不存在"));
            throw new ServiceException(StatusCode.USERNAME_ERROR);
        }
        if (!userLoginDTO.getPassword().equals(userVO.getPassword())){
            System.out.println(("输入的密码不正确"));
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
