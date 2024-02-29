package cn.tedu.asset.manage.pojo.po;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
@Data
public class AssetPO implements Serializable {
    private String code;
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