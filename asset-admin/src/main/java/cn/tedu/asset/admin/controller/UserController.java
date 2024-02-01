package cn.tedu.asset.admin.controller;

import cn.tedu.asset.admin.pojo.dto.UserLoginDTO;
import cn.tedu.asset.admin.pojo.dto.UserUpdateDTO;
import cn.tedu.asset.admin.pojo.param.UserUpdateInfoParam;
import cn.tedu.asset.admin.pojo.vo.UserVO;
import cn.tedu.asset.admin.service.IUserService;
import cn.tedu.asset.commom.response.JsonResult;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;

@Slf4j
@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    private IUserService userService;

    @ApiOperation("用户登录")
    @ApiOperationSupport(order = 290)
    @PostMapping("/login")
    public JsonResult login(UserLoginDTO userLoginDTO){
        UserVO userVO = userService.login(userLoginDTO);
        System.out.println("userVO="+userVO);
        return JsonResult.ok(userVO);
    }

    @PostMapping("/{id:[0-9]+}/info/update")
//    @PreAuthorize("hasAuthority('/account/user/edit')")
    @ApiOperation("修改基本信息")
    @ApiOperationSupport(order = 300)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long")
    })
    public JsonResult updateInfo(@PathVariable @Range(min = 1, message = "请提交合法的用户ID值！") Long id,
                                 @Valid UserUpdateInfoParam userUpdateInfoParam) {
        log.debug("开始处理【修改基本信息】的请求，用户：{}，新基本信息：{}", id, userUpdateInfoParam);
        userService.updateInfo(id, userUpdateInfoParam);
        return JsonResult.ok();
    }

    @PostMapping("/{id:[0-9]+}/password/update")
//    @PreAuthorize("hasAuthority('/account/user/edit')")
    @ApiOperation("修改密码")
    @ApiOperationSupport(order = 310)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "long"),
            @ApiImplicitParam(name = "newPassword", value = "新密码", required = true, paramType = "query")
    })
    public JsonResult updatePassword(@PathVariable @Range(min = 1, message = "请提交合法的用户ID值！") Long id,
                                     @Pattern(regexp = "^[a-zA-Z]{1}[a-zA-Z0-9_]{3,14}$", message = "密码必须是4~15长度的字符组成") String newPassword) {
        log.debug("开始处理【修改密码】的请求，用户：{}，新密码：{}", id, newPassword);
        userService.updatePassword(id, newPassword);
        return JsonResult.ok();
    }


}
