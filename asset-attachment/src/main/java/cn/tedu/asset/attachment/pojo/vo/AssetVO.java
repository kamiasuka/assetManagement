package cn.tedu.asset.attachment.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class AssetVO implements Serializable {
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
    private String approvalDate;
    private String note;
}
