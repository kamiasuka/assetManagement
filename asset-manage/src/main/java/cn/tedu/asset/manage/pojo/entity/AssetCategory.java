package cn.tedu.asset.manage.pojo.entity;

import lombok.Data;

@Data
public class AssetCategory {
    private Integer id;
    private String name;
    private int parentId;
    private int level;
    private int isParent;
}
