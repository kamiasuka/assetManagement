package cn.tedu.asset.manage.pojo.vo;

import lombok.Data;

@Data
public class InComeVO {
    private Long id;
    private String billNumber;
    private String incomeType;
    private String assetCode;
    private String assetName;
    private double incomeAmount;
    private String entryStatus;
    private String entryTime;

}
