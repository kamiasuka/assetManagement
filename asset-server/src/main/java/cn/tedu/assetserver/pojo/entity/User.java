package cn.tedu.assetserver.pojo.entity;

import lombok.Data;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@Data
public class User {
    private Integer id;
    private String username;
    private String password;
    private String character;
    private String tel;
    private String email;
    private String dept;
    private String unit;

    public void setCreateTime(Date date) {
    }

    public void setIsAdmin(int i) {
    }
}