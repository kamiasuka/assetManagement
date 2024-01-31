package cn.tedu.asset.admin.pojo.entity;

import lombok.Data;

@Data
public class User {
    private String username;
    private String password;
    private String nickname;
    private String identity;
    private String tel;
    private String email;
    private String dept;
    private String unit;
}