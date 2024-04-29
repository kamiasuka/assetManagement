package cn.tedu.asset.attachment.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ListAdminVO {
    private Long id;
    private String unit;
    private String name;
    private String type;
    private String tip;
    private String assetName;
    private String useStatus;
    private String url;
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date updatedTime;
}
