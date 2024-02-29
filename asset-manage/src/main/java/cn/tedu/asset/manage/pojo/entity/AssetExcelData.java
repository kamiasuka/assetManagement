package cn.tedu.asset.manage.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AssetExcelData {
    @ExcelProperty("资产编码")
    private String code;

    @ExcelProperty("资产名")
    private String name;

    @ExcelProperty("资产类型")
    private String type;

    @ExcelProperty("部门")
    private String dept;

    @ExcelProperty("单位")
    private String unit;

    @ExcelProperty("使用年限")
    private Integer life;

    @ExcelProperty("资产价值")
    private Double amount;

    @ExcelProperty("使用状态")
    private String useStatus;
}
