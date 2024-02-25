package cn.tedu.asset.manage.pojo.po;

import lombok.Data;

import java.util.Date;
@Data
public class AssetPO {
    private String id;
    private String name;
    private String type;
    private String dept;
    private String unit;
    private int life;
    private double amount;
    private String userStatus;
    private String reviewStatus;
    private Date approvalDate;
    private String note;
}
