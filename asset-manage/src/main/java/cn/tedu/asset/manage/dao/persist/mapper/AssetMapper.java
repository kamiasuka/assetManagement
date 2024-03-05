package cn.tedu.asset.manage.dao.persist.mapper;

import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import com.github.pagehelper.Page;

import java.util.List;

public interface AssetMapper {
    List<String> listAllCategory();

    List<AssetPO> listAssetByCategory(String type);

    List<String> listByLevel(int level);

    Integer numberSumQuery(String maxType);

    Double worthSumQuery(String maxType);

    List<AssetPO> export();

    List<AssetVO> exportVo();

    List<AssetPO> exportNoReview();

    int insertNew(AssetPO assetPO);


    List<AssetPO> exportByType();

    //资产变更
    AssetVO listAllAdd();

    AssetVO listAllChange();

    int assetUpdate(String code);//更新审核状态

    int ReviewAssetUpdate(AssetUpdatePO assetUpdatePO);//临时存储待审核的数据

    AssetUpdateDTO getSubmitAsset(String code);

    void saveSubmitAsset(AssetUpdateDTO assetUpdateDTO);//存储已审核的数据


}
