package cn.tedu.asset.manage.pojo.dto;

import lombok.Data;

@Data
public class AssetSaleSubmitSDTO {
    private Double revenueMoney;
    private String saleTime;
    private String receiver;
    private String assetCode;
    private String assetName;
    private Double assetValue;
    private String contractNumber;
}
