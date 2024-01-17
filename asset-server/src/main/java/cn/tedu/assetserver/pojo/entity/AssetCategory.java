package cn.tedu.assetserver.pojo.entity;

import lombok.Data;

@Data
public class AssetCategory {
    private Integer id;
    private String name;
    private int parentId;
    private int level;
    private String type;
    private int isParent;
}
