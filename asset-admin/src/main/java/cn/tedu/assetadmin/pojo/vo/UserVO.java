package cn.tedu.assetadmin.pojo.vo;

import lombok.Data;

@Data
public class UserVO {
    private String username;
    private String password;
    private String nickname;
    private String identity;
    private String tel;
    private String email;
    private String dept;
    private String unit;
}
