package cn.tedu.assetserver.pojo.vo;

import lombok.Data;

@Data
public class ContentUpdateVO {
    private Long id;
    private String title;
    private String content;
    private Integer type;
    private Integer categoryId;
    private String imgUrl;
    private String videoUrl;
}
