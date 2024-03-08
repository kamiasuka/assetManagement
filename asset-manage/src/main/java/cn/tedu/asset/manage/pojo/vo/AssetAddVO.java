package cn.tedu.asset.manage.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AssetAddVO {
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
    private String submitDate;
    private String approvalDate;
    private String note;
}
