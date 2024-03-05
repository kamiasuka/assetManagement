package cn.tedu.asset.admin.pojo.vo;

import lombok.Data;

/**
 *用户登录结果的VO类
 */

@Data
public class LoginResultVO {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String nickname;
    /**
     * 身份
     */
    private String identity;
}
