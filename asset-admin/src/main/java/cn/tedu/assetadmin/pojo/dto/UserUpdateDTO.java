package cn.tedu.assetadmin.pojo.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private String username;
    private String password;
    private String nickname;
    private String identity;
    private String tel;
    private String email;
    private String dept;
    private String unit;
}
