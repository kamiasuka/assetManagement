package cn.tedu.assetserver.pojo.entity;

import lombok.Data;

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
}

