package cn.tedu.asset.manage.pojo.dto;

import lombok.Data;

import java.util.Date;
@Data
public class AssetUpdateDTO {
    private String code;
    private String name;
    private String type;
    private String dept;
    private String unit;
    private int life;
    private double amount;
    private String userStatus;
    private String reviewStatus;
    private Date submitDate;
    private Date approvalDate;
    private String note;
}
