package cn.tedu.asset.manage.pojo.dto;

import lombok.Data;

@Data
public class AssetAddDTO {
    private String code;
    private String name;
    private String type;
    private String maxType;
    private String dept;
    private String unit;
    private Integer life;
    private Double amount;
}
