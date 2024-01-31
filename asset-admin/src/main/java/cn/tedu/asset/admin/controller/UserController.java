package cn.tedu.asset.admin.controller;

import cn.tedu.asset.admin.pojo.dto.UserLoginDTO;
import cn.tedu.asset.admin.pojo.dto.UserUpdateDTO;
import cn.tedu.asset.admin.pojo.vo.UserVO;
import cn.tedu.asset.admin.service.IUserService;
import cn.tedu.asset.commom.response.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    IUserService userService;
    @PostMapping("login")
    public JsonResult login(UserLoginDTO userLoginDTO){
        UserVO userVO = userService.login(userLoginDTO);
        System.out.println("userVO="+userVO);
        return JsonResult.ok(userVO);
    }
    @PostMapping("updateUser")
    public JsonResult updateUser(UserUpdateDTO userUpdateDTO){
        userService.updateUser(userUpdateDTO);
        return JsonResult.ok();
    }
}
