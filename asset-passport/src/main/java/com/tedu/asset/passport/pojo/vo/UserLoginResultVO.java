package com.tedu.asset.passport.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 用户登录结果的VO类
 *
 * @author java@tedu.cn
 * @version 3.0
 */
@Data
@Accessors(chain = true)
public class UserLoginResultVO implements Serializable {

    /**
     * 用户ID
     */
    @ApiModelProperty("用户ID")
    private Long id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    private String username;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nickname;

    /**
     * Token（JWT）
     */
    @ApiModelProperty("Token（JWT）")
    private String token;

    /**
     * 权限清单
     */
    @ApiModelProperty("权限清单")
    private List<String> authorities;
}
