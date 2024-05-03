package cn.tedu.asset.admin;

import cn.tedu.asset.admin.dao.persist.mapper.UserMapper;
import cn.tedu.asset.admin.pojo.vo.UserVO;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

//@SpringBootTest
public class UserTest {
    @Autowired(required = false)
    UserMapper userMapper;

    @Test
    public void test1(){
        Map<String,Object> claims = new HashMap<>();
        claims.put("id",1);
        claims.put("user","admin");

        String jwt = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256,"asset")//签名算法
                .setClaims(claims) //自定义内容(载荷)
                .setExpiration(new Date(System.currentTimeMillis() + 60*1000)) //有效期
                .compact();

        System.out.println("JWT令牌："+jwt);
    }

    @Test
    public void parseJwt(){
        Claims claims = Jwts.parser()
                .setSigningKey("asset")//指定签名密钥（必须保证和生成令牌时使用相同的签名密钥）
                .parseClaimsJws("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6MSwiZXhwIjoxNzE0NjM2Nzk3LCJ1c2VyIjoiYWRtaW4ifQ.dP5NJmAk1K6Jwp6aFMnU4Idr83bKGf_T5bvFXHiZ2AI")
                .getBody();

        System.out.println(claims);
    }

}
