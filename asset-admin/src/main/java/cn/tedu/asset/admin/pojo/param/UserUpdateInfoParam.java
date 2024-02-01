package cn.tedu.asset.admin.pojo.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserUpdateInfoParam implements Serializable {
    private String nickname;
    private String tel;
    private String email;
}
