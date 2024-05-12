package cn.tedu.asset.manage.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class AssetSale {
    private int id;
    private Double revenueMoney;
    private Date saleTime;
    private String receiver;
    private String assetCode;
    private String assetName;
    private Double assetValue;
    private String contractNumber;
    private String approvalStatus;
    private Date approvalDate;
}
