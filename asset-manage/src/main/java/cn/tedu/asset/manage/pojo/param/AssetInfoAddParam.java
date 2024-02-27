package cn.tedu.asset.manage.pojo.param;

import lombok.Data;

@Data
public class AssetInfoAddParam {
    private String code;
    private String name;
    private String type;
    private String unit;
    private Integer life;
    private Double amount;
}
