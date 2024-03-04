package com.tedu.asset.passport.pojo.vo;

import lombok.Data;
import java.io.Serializable;
import java.util.List;

@Data
public class UserLoginInfoVO implements Serializable {
    /**
     * 数据id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码（密文）
     */
    private String password;
    /**
     * 用户名
     */
    private String nickname;
    /**
     * 权限列表
     */
    private List<String> permissions;
}
