package cn.tedu.asset.manage.pojo.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class AssetInfo implements Serializable {
    private Long id;
    private String code;
    private String name;
    private String type;
    private String dept;
    private String unit;
    private Integer life;
    private Double amount;
    private String useStatus;
    private String reviewStatus;
    private Date approvalDate;
}
