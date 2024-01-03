package cn.tedu.assetserver.service.impl;

import cn.tedu.assetserver.exception.ServiceException;
import cn.tedu.assetserver.mapper.UserMapper;
import cn.tedu.assetserver.pojo.dto.UserLoginDTO;
import cn.tedu.assetserver.pojo.dto.UserRegDTO;
import cn.tedu.assetserver.pojo.dto.UserUpdateDTO;
import cn.tedu.assetserver.pojo.entity.User;
import cn.tedu.assetserver.pojo.vo.UserAdminVO;
import cn.tedu.assetserver.pojo.vo.UserVO;
import cn.tedu.assetserver.response.StatusCode;
import cn.tedu.assetserver.service.IUserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
public class UserService implements IUserService {
    @Autowired
    UserMapper mapper;
    @Override
    public void reg(UserRegDTO userRegDTO) {
        UserVO userVO = mapper.selectByUsername(userRegDTO.getUsername());
        if (userVO!=null){
            throw new ServiceException(StatusCode.USERNAME_ALREADY_EXISTS);
        }
        User user = new User();
        BeanUtils.copyProperties(userRegDTO,user);
        user.setCreateTime(new Date());
        user.setIsAdmin(0); //默认不是管理员
        mapper.insert(user);
    }

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

    @Override
    public List<UserAdminVO> list() {
        return mapper.select();
    }
    @Value("${file-path}")
    private String dirPath;
    @Override
    public void deleteById(Long id) {

        String imgUrl = mapper.selectUrlById(id);
        new File(dirPath+imgUrl).delete();
        mapper.deleteById(id);
    }
}
