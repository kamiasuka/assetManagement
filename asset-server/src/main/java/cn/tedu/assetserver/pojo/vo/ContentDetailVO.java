package cn.tedu.assetserver.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ContentDetailVO {
    private Long id;
    private String title;
    private String brief;
    private String content;
    private String nickname;
    private String userImgUrl;
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date createTime;
    private Integer viewCount;
    private Long userId;
    private Integer type;
    private String videoUrl;
}
