package cn.tedu.asset.server.pojo.dto;

import lombok.Data;

@Data
public class CategoryAddDTO {
    private String name;
    private int parentId;
    private String type;
}
