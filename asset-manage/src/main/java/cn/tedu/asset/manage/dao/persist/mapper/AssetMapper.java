package cn.tedu.asset.manage.dao.persist.mapper;

import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;

import java.util.List;

public interface AssetMapper {
    List<String> listAllCategory();

    List<AssetPO> listAssetByCategory(String type);

    List<String> listByLevel(int level);

    Integer numberSumQuery(String maxType);

    Double worthSumQuery(String maxType);

    List<AssetPO> export();
    List<AssetPO> exportNoReview();

    List<AssetPO> exportByType();

    int assetUpdate(AssetUpdatePO assetUpdatePO);

}
