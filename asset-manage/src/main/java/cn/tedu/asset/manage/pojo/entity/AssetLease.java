package cn.tedu.asset.manage.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AssetLease {
    private String leaseType;
    private String totalTime;
    private String lessee;
    private double revenueMoney;
    private Date leaseDate;
    private String assetCode;
    private String assetName;
    private String contractNumber;
    private String approvalStatus;
    private String approvalDate;
}
