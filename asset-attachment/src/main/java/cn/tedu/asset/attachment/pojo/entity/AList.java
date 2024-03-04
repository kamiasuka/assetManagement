package cn.tedu.asset.attachment.pojo.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class AList {
    private Long id;
    private String unit;
    private String Type;
    private String Tip;
    private String atName;
    private String Status;
    private String Url;
    @JsonFormat(pattern = "yyyy/MM/dd",timezone = "GMT+8")
    private Date updatedTime;


}
