package cn.tedu.asset.manage.pojo.dto;

import lombok.Data;

/**
 * 用于传递给首页，显示首页数据
 */
@Data
public class AssetStatisticDTO {
    /**
     * 该一级分类下所有资产的总值
     */
    private Double worth;
    /**
     * 分类类型
     */
    private String type;
    /**
     * 该一级分类下所有资产的数量
     */
    private Integer num;
}
