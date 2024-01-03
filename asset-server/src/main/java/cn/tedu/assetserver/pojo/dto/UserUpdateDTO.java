package cn.tedu.assetserver.pojo.dto;

import lombok.Data;

@Data
public class UserUpdateDTO {
    private Long id;
    private String nickname;
    private String imgUrl;
    private Integer isAdmin;
}
