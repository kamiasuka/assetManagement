package cn.tedu.assetserver.controller;

import cn.tedu.assetserver.pojo.dto.UserLoginDTO;
import cn.tedu.assetserver.pojo.dto.UserRegDTO;
import cn.tedu.assetserver.pojo.dto.UserUpdateDTO;
import cn.tedu.assetserver.pojo.vo.UserAdminVO;
import cn.tedu.assetserver.pojo.vo.UserVO;
import cn.tedu.assetserver.common.response.JsonResult;
import cn.tedu.assetserver.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/users")
public class UserController {
    @Autowired
    IUserService service;
    @PostMapping("reg")
    public JsonResult reg(UserRegDTO userRegDTO){
        service.reg(userRegDTO);
        return JsonResult.ok();
    }
    @PostMapping("login")
    public JsonResult login(UserLoginDTO userLoginDTO){

        UserVO userVO = service.login(userLoginDTO);
        System.out.println("userVO="+userVO);
        return JsonResult.ok(userVO);
    }
    @PostMapping("update")
    public JsonResult update(UserUpdateDTO userUpdateDTO){
        service.update(userUpdateDTO);
        return JsonResult.ok();
    }
    @GetMapping("")
    public JsonResult list(){
        List<UserAdminVO> list = service.list();
        return JsonResult.ok(list);
    }
    @PostMapping("{id}/delete")
    public JsonResult deleteById(@PathVariable Long id){
        service.deleteById(id);
        return JsonResult.ok();
    }
}
