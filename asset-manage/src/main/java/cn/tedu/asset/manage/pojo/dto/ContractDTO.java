package cn.tedu.asset.manage.pojo.dto;

import lombok.Data;

@Data
public class ContractDTO {
    private String contractNumber;
    private String contractType;
    private String partyA;
    private String partyB;
    private String signingDate;
    private String terminationDate;
}
