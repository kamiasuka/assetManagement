package cn.tedu.asset.manage.pojo.dto;

import lombok.Data;

@Data
public class AssetLeaseSubmitSDTO {
    private String leaseType;
    private String totalTime;
    private String lessee;
    private double revenueMoney;
    private String leaseDate;
    private String assetCode;
    private String assetName;
    private String contractNumber;
}
