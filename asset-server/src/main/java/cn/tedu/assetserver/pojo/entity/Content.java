package cn.tedu.assetserver.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Content {
    private Long id;
    private String title;
    private String imgUrl;
    private String videoUrl;
    private String content;
    private String brief;
    private Integer type;
    private Integer viewCount;
    private Integer commentCount;
    private Long userId;
    private Long updateBy;
    private Long categoryId;
    private Date createTime;
    private Date updateTime;


}
