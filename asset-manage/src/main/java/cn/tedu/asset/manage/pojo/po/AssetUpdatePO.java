package cn.tedu.asset.manage.pojo.po;

import lombok.Data;

import java.util.Date;
@Data
public class AssetUpdatePO {
    private String code;
    private String name;
    private String type;
    private String maxType;
    private String dept;
    private String unit;
    private int life;
    private double amount;
    private String useStatus;
    private String reviewStatus;
    private Date submitDate;
    private Date approvalDate;
    private String note;
}
