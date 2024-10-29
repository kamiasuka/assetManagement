package cn.tedu.asset.admin.service.impl;

import cn.tedu.asset.admin.dao.persist.mapper.UserMapper;
import cn.tedu.asset.admin.dao.persist.repository.IUserRepository;
import cn.tedu.asset.admin.pojo.dto.UserInfoUpdateDTO;
import cn.tedu.asset.admin.pojo.dto.UserLoginDTO;
import cn.tedu.asset.admin.pojo.entity.User;
import cn.tedu.asset.admin.pojo.vo.LoginResultVO;
import cn.tedu.asset.admin.pojo.vo.UserVO;
import cn.tedu.asset.admin.service.IUserService;
import cn.tedu.asset.commom.jwt.JwtUtils;
import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class UserServiceImpl implements IUserService {
    @Autowired(required = false)
    private UserMapper userMapper;

    @Autowired(required = false)
    private IUserRepository userRepository;

    @Override
    public LoginResultVO login(UserLoginDTO userLoginDTO) {
        String password = userLoginDTO.getPassword();
        String md5password = DigestUtils.md5DigestAsHex(password.getBytes());
        log.info("对密码进行MD5加密");
        UserVO userVO = userMapper.getByUsername(userLoginDTO.getUsername());
        if (userVO==null){
            System.out.println(("用户名并不存在"));
            throw new ServiceException(StatusCode.USERNAME_ERROR);
        }
        if (!md5password.equals(userVO.getPassword())){
            System.out.println(("输入的密码不正确"));
            throw new ServiceException(StatusCode.PASSWORD_ERROR);
        }
        LoginResultVO loginResultVO = new LoginResultVO();
        BeanUtils.copyProperties(userVO,loginResultVO);
        log.debug("登录成功");
        //创建令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("id",userVO.getId());
        claims.put("username",userVO.getUsername());
        claims.put("nickname",userVO.getNickname());
        claims.put("identity",userVO.getIdentity());
        String jwt = JwtUtils.generateJwt(claims);
        loginResultVO.setJwt(jwt);
        System.out.println("下发JWT："+jwt);
        return loginResultVO;
    }

    @Override
    public void updateInfo(Long userId, UserInfoUpdateDTO userInfoUpdateDTO) {
        log.debug("开始处理【修改基本信息】的业务，用户ID：{}，新基本信息：{}", userId, userInfoUpdateDTO);
        User user = new User();
        BeanUtils.copyProperties(userInfoUpdateDTO, user);
        user.setId(userId);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = "修改基本信息失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED,message);
        }
    }

    @Override
    public void updatePassword(Long userId, String newPassword) {
        log.debug("开始处理【修改密码】的业务，用户ID：{}，新密码：{}", userId, newPassword);
        User user = new User();
        user.setId(userId);
        user.setPassword(newPassword);
        int rows = userRepository.updateById(user);
        if (rows != 1) {
            String message = "修改密码失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED,message);
        }
    }

    @Override
    public UserVO getInfoById(Long id) {
        return userMapper.getById(id);
    }

}
