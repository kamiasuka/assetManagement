package cn.tedu.assetserver.pojo.dto;

import lombok.Data;

@Data
public class CommentDTO {
    private String content;
    private Long userId;
    private Long contentId;
}
