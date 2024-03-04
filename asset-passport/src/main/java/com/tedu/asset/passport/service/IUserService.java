package com.tedu.asset.passport.service;

import cn.tedu.asset.commom.pojo.authentication.CurrentPrincipal;
import com.tedu.asset.passport.pojo.dto.UserLoginInfoDTO;
import com.tedu.asset.passport.pojo.vo.UserLoginResultVO;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface IUserService {
    /**
     * 用户登录
     *
     * @param userLoginInfoDTO 封装了登录信息的对象
     * @param remoteAddr         客户端的IP地址
     * @param userAgent          客户端的浏览器信息
     * @return 成功登录的用户的信息，包括：ID、用户名、JWT等数据
     */
    UserLoginResultVO login(UserLoginInfoDTO userLoginInfoDTO, String remoteAddr, String userAgent);
    /**
     * 退出登录
     *
     * @param currentPrincipal 当事人
     */
    void logout(CurrentPrincipal currentPrincipal);
}
