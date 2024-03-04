package com.tedu.asset.passport.service.impl;


import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.pojo.po.UserCachePO;
import cn.tedu.asset.commom.response.StatusCode;
import cn.tedu.asset.commom.util.JwtUtils;
import cn.tedu.asset.commom.pojo.authentication.CurrentPrincipal;
import com.alibaba.fastjson2.JSON;
import com.tedu.asset.passport.dao.cache.IUserCacheRepository;
import com.tedu.asset.passport.dao.persist.repository.IUserRepository;
import com.tedu.asset.passport.pojo.dto.UserLoginInfoDTO;
import com.tedu.asset.passport.pojo.vo.UserLoginInfoVO;
import com.tedu.asset.passport.pojo.vo.UserLoginResultVO;
import com.tedu.asset.passport.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static cn.tedu.asset.commom.consts.web.JwtConsts.*;

/**
 * 处理用户数据的业务实现类
 *
 * @author java@tedu.cn
 * @version 3.0
 */
@Slf4j
@Service
public class UserServiceImpl implements IUserService {

    @Value("${tmall.jwt.secret-key}")
    private String secretKey;
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUserCacheRepository userCacheRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserServiceImpl() {
        log.debug("创建业务类对象：UserServiceImpl");
    }

    @Override
    public UserLoginResultVO login(UserLoginInfoDTO userLoginInfoDTO,
                                   String remoteAddr, String userAgent) {
        log.debug("开始处理【用户登录】的业务，参数：{}", userLoginInfoDTO);
        String username = userLoginInfoDTO.getUsername();
        UserLoginInfoVO loginInfo = userRepository.getLoginInfoByUsername(username);
        if (loginInfo == null) {
            String message = "登录失败，用户名或密码错误！（开发阶段专用：用户名错误）";
            log.warn(message);
            throw new ServiceException(StatusCode.USERNAME_ERROR, message);
        }


        String password = userLoginInfoDTO.getPassword();
        if (!passwordEncoder.matches(password, loginInfo.getPassword())) {
            String message = "登录失败，用户名或密码错误！（开发阶段专用：密码错误）";
            log.warn(message);
            throw new ServiceException(StatusCode.PASSWORD_ERROR, message);
        }

        Long userId = loginInfo.getId();
        String nickname = loginInfo.getNickname();

        List<GrantedAuthority> authorities = new ArrayList<>();
        List<String> permissions = loginInfo.getPermissions();
        for (String permission : permissions) {
            authorities.add(new SimpleGrantedAuthority(permission));
        }
        String authoritiesJsonString = JSON.toJSONString(authorities);



        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_USER_ID, userId);
        claims.put(CLAIM_USER_NAME, username);
        claims.put(CLAIM_USER_AGENT, userAgent);
        claims.put(CLAIM_REMOTE_ADDR, remoteAddr);
        String jwt = JwtUtils.generate(claims, secretKey);
        log.debug("生成用户的JWT数据：{}", jwt);

        UserCachePO userCachePO = new UserCachePO();
        userCachePO.setAuthoritiesJsonString(authoritiesJsonString);
        userCacheRepository.saveUserState(userId, userCachePO);
        log.debug("向缓存中存入用户状态数据：{}", userCachePO);

        UserLoginResultVO userLoginResultVO = new UserLoginResultVO()
                .setId(userId)
                .setUsername(username)
                .setNickname(nickname)
                .setToken(jwt)
                .setAuthorities(permissions);
        log.debug("即将返回用户的登录结果：{}", userLoginResultVO);
        return userLoginResultVO;
    }

    @Override
    public void logout(CurrentPrincipal currentPrincipal) {
        log.debug("开始处理【退出登录】的业务，参数：{}", currentPrincipal);
        Long userId = currentPrincipal.getId();
        boolean deleteResult = userCacheRepository.deleteUserState(userId);
        if (!deleteResult) {
            String message = "操作失败，用户数据有误！";
            log.warn(message);
            throw new ServiceException( StatusCode.OPERATION_FAILED, message);
        }
    }

}