package cn.tedu.asset.commom.pojo.po;

import lombok.Data;

import java.io.Serializable;

/**
 * 用户状态数据的缓存PO类，此类数据将存入到缓存中，用户提交请求时也会实时检查缓存中的用户状态
 *
 * @author java@tedu.cn
 * @version 3.0
 */
@Data
public class UserCachePO implements Serializable {
    /**
     * 权限列表的JSON字符串
     */
    private String authoritiesJsonString;
}
