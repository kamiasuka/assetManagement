package cn.tedu.asset.manage.dao.persist.mapper;

import cn.tedu.asset.manage.pojo.po.AssetPO;

import java.util.List;

public interface AssetMapper {
    List<String> listAllCategory();

    List<AssetPO> listAssetByCategory(String type);
}
