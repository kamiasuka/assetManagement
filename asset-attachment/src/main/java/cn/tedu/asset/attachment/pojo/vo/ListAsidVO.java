package cn.tedu.asset.attachment.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class ListAsidVO {
    private String assetId;
    private String Type;
    private String atName;
    private String name;
    private String Status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String updatedTime;

}
