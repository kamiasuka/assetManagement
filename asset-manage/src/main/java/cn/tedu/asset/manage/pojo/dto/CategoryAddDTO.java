package cn.tedu.asset.manage.pojo.dto;

import lombok.Data;

@Data
public class CategoryAddDTO {
    private String name;
    private int parentId;
}
