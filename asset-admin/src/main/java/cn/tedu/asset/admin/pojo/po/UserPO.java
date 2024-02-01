package cn.tedu.asset.admin.pojo.po;

import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@Accessors(chain = true)
public class UserPO implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String nickname;
    private String identity;
    private String tel;
    private String email;
    private String dept;
    private String unit;
}
