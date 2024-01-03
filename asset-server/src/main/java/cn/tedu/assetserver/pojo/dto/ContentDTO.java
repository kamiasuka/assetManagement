package cn.tedu.assetserver.pojo.dto;

import lombok.Data;

@Data
public class ContentDTO {
    private Long id;

    private String title;
    private String imgUrl;
    private String videoUrl;
    private String content;
    private String brief;
    private Integer type;
    private Long userId;
    private Long categoryId;



}
