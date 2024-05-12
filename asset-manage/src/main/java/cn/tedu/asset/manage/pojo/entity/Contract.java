package cn.tedu.asset.manage.pojo.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Contract {
    private String contractNumber;
    private String contractType;
    private String partyA;
    private String partyB;
    private Date signingDate;
    private Date terminationDate;
}
