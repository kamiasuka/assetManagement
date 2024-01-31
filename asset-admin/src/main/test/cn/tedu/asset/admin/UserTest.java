package cn.tedu.asset.admin;

import cn.tedu.asset.admin.dao.persist.mapper.UserMapper;
import cn.tedu.asset.admin.pojo.vo.UserVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {
    @Autowired(required = false)
    UserMapper userMapper;

    @Test
    public void test1(){
        UserVO userVO = userMapper.selectByUsername("admin");
        System.out.println(userVO);
    }


}
