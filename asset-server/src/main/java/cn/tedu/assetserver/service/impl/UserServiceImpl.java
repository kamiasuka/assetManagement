package cn.tedu.assetserver.service.impl;

import cn.tedu.assetserver.common.response.StatusCode;
import cn.tedu.assetserver.exception.ServiceException;
import cn.tedu.assetserver.mapper.UserMapper;
import cn.tedu.assetserver.pojo.dto.UserLoginDTO;
import cn.tedu.assetserver.pojo.dto.UserUpdateDTO;
import cn.tedu.assetserver.pojo.entity.User;
import cn.tedu.assetserver.pojo.vo.UserVO;
import cn.tedu.assetserver.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    UserMapper mapper;

    @Override
    public UserVO login(UserLoginDTO userLoginDTO) {
        UserVO userVO = mapper.selectByUsername(userLoginDTO.getUsername());
        if (userVO==null){
            throw new ServiceException(StatusCode.USERNAME_ERROR);
        }
        if (!userLoginDTO.getPassword().equals(userVO.getPassword())){
            throw new ServiceException(StatusCode.PASSWORD_ERROR);
        }
        return userVO;
    }

    @Override
    public void update(UserUpdateDTO userUpdateDTO) {
        User user = new User();
        BeanUtils.copyProperties(userUpdateDTO,user);
        mapper.update(user);
    }
}
