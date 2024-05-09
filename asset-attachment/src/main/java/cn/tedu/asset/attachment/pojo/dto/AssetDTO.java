package cn.tedu.asset.attachment.pojo.dto;

import lombok.Data;

@Data
public class AssetDTO {
    private String assetCode;
    private String assetName;//入参改为资产名称
    private String type;
    private String tip;
    private String url;
}
