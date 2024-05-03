package cn.tedu.asset.manage.pojo.vo;

import lombok.Data;

@Data
public class AssetCategoryVO {
    private int id;
    private String name;
    private int parentId;
    private int level;
    private int isParent;
}
