package cn.tedu.asset.admin.service.impl;

import cn.tedu.asset.admin.dao.persist.mapper.UserMapper;
import cn.tedu.asset.admin.dao.persist.repository.IUserRepository;
import cn.tedu.asset.admin.pojo.dto.UserLoginDTO;
import cn.tedu.asset.admin.pojo.dto.UserUpdateDTO;
import cn.tedu.asset.admin.pojo.entity.User;
import cn.tedu.asset.admin.pojo.param.UserUpdateInfoParam;
import cn.tedu.asset.admin.pojo.po.UserPO;
import cn.tedu.asset.admin.pojo.vo.UserVO;
import cn.tedu.asset.admin.service.IUserService;
import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private IUserRepository userRepository;

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
    public void updateInfo(Long userId, UserUpdateInfoParam userUpdateInfoParam) {
        log.debug("开始处理【修改基本信息】的业务，用户ID：{}，新基本信息：{}", userId, userUpdateInfoParam);
        UserPO userPO = new UserPO();
        BeanUtils.copyProperties(userUpdateInfoParam, userPO);
        userPO.setId(userId);
        int rows = userRepository.updateById(userPO);
        if (rows != 1) {
            String message = "修改基本信息失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED,message);
        }
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        log.debug("开始处理【修改密码】的业务，用户ID：{}，新密码：{}", userId, newPassword);
        UserPO userPO = new UserPO();
        userPO.setId(userId);
        userPO.setPassword(newPassword);
        int rows = userRepository.updateById(userPO);
        if (rows != 1) {
            String message = "修改密码失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED,message);
        }
    }
}
