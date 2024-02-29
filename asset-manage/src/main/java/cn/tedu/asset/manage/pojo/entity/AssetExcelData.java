package cn.tedu.asset.manage.pojo.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.annotation.write.style.ColumnWidth;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AssetExcelData {
    @ColumnWidth(12)
    @ExcelProperty("资产编码")
    private String code;

    @ExcelProperty("资产名")
    private String name;

    @ColumnWidth(12)
    @ExcelProperty("资产类型")
    private String type;

    @ExcelProperty("部门")
    private String dept;

    @ExcelProperty("单位")
    private String unit;

    @ColumnWidth(12)
    @ExcelProperty("使用年限")
    private Integer life;

    @ColumnWidth(12)
    @ExcelProperty("资产价值")
    private Double amount;

    @ColumnWidth(12)
    @ExcelProperty("使用状态")
    private String useStatus;
}
