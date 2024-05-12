package cn.tedu.asset.manage.pojo.vo;

import lombok.Data;

import java.util.Date;

@Data
public class ContractVO {
    private String contractNumber;
    private String contractType;
    private String partyA;
    private String partyB;
    private String signingDate;
    private String terminationDate;
}
