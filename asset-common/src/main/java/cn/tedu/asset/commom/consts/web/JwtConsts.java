package cn.tedu.asset.commom.consts.web;

/**
 * JWT的相关常量
 *
 * @author java@tedu.cn
 * @version 3.0
 */
public interface JwtConsts {

    /**
     * JWT最小长度值
     */
    int JWT_MIN_LENGTH = 100;

    /**
     * 用户ID
     */
    String CLAIM_USER_ID = "id";

    /**
     * 用户名
     */
    String CLAIM_USER_NAME = "username";

    /**
     * 浏览器信息
     */
    String CLAIM_USER_AGENT = "userAgent";

    /**
     * IP地址
     */
    String CLAIM_REMOTE_ADDR = "ip";

}
