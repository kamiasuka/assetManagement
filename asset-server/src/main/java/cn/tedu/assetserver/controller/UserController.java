package cn.tedu.assetserver.controller;

import cn.tedu.assetserver.pojo.dto.UserLoginDTO;
import cn.tedu.assetserver.pojo.dto.UserUpdateDTO;
import cn.tedu.assetserver.pojo.vo.UserVO;
import cn.tedu.assetserver.common.response.JsonResult;
import cn.tedu.assetserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
