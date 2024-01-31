package cn.tedu.asset.server.pojo.vo;

import lombok.Data;

@Data
public class CategoryStandardVO {
    private String name;
    private int parentId;
    private int level;
    private String type;
    private int isParent;
}
