package cn.tedu.asset.attachment.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class ListVO {
    private String unit;
    private String Type;
    private String Name;
    private String Status;
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private String updatedTime;

}
