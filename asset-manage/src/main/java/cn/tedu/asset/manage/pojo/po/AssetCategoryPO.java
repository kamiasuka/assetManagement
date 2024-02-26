package cn.tedu.asset.manage.pojo.po;

import lombok.Data;

@Data
public class AssetCategoryPO {
    private String name;
    private int parentId;
    private int level;
    private int isParent;
}
