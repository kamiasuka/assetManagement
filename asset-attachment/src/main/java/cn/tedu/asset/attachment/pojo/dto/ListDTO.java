package cn.tedu.asset.attachment.pojo.dto;

import lombok.Data;

@Data
public class ListDTO {
    private Long id;
    private String assetId;
    private String ainame;//入参改为资产名称
    private String Type;
    private String atName;
    private String Tip;
    private String Url;
}
