package cn.tedu.asset.attachment.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AttachmentPO {
    private Long id;
    private String attachmentCode;
    private String assetCode;
    private String assetName;
    private String type;
    private String tip;
    private String status;
    private String url;
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date updatedTime;


}
