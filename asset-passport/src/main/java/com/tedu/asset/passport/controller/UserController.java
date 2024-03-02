package com.tedu.asset.passport.controller;

import cn.tedu.asset.commom.consts.web.HttpConsts;
import cn.tedu.asset.commom.response.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.tedu.asset.passport.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;

/**
 * 处理用户相关请求的控制器类
 *
 * @author java@tedu.cn
 * @version 3.0
 */
@Slf4j
@RestController
@RequestMapping("/passport")
@Validated
@Api(tags = "1. 单点登录")
public class UserController implements HttpConsts {

}
