package cn.tedu.asset.commom.pojo.authentication;

import lombok.Data;

import java.io.Serializable;

/**
 * 当前认证信息中的当事人
 *
 * @author java@tedu.cn
 * @version 3.0
 */
@Data
public class CurrentPrincipal implements Serializable {

    /**
     * 当事人ID
     */
    private Long id;

    /**
     * 当事人用户名
     */
    private String username;

}
