package cn.tedu.asset.manage.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AssetSaleVO {
    private int id;
    private Double revenueMoney;
    private String saleTime;
    private String receiver;
    private String assetCode;
    private String assetName;
    private Double assetValue;
    private String contractNumber;
    private String approvalStatus;
    private String approvalDate;
}
