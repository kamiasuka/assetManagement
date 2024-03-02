package cn.tedu.asset.commom.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Map;

/**
 * JWT工具类
 *
 * @author java@tedu.cn
 * @version 3.0
 */
public class JwtUtils {

    /**
     * 生成JWT
     *
     * @param claims    存入到JWT中的数据
     * @param secretKey 密钥
     * @return JWT数据
     */
    public static synchronized String generate(Map<String, Object> claims, String secretKey) {
        return Jwts.builder()
                .setHeaderParam("alg", "HS256")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256, secretKey)
                .compact();
    }

    /**
     * 解析JWT
     *
     * @param jwt       JWT数据
     * @param secretKey 生成JWT时使用的密钥
     * @return 解析结果
     */
    public static synchronized Claims parse(String jwt, String secretKey) {
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

}
