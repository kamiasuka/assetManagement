package cn.tedu.asset.manage.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class InCome {
    private Long id;
    private String incomeType;
    private String assetCode;
    private String assetName;
    private double incomeAmount;
    private String entryStatus;
    private Date entryTime;
}
