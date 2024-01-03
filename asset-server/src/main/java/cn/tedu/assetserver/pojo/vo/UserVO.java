package cn.tedu.assetserver.pojo.vo;

import lombok.Data;

@Data
public class UserVO {
    private Long id;
    private String password;
    private String nickname;
    private Integer isAdmin;
    private String imgUrl;
    private String username;

}
