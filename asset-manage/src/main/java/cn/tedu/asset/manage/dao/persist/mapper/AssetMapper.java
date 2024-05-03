package cn.tedu.asset.manage.dao.persist.mapper;

import cn.tedu.asset.manage.pojo.vo.TypeListVO;
import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetAddVO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import com.github.pagehelper.Page;

import java.util.List;

public interface AssetMapper {
    List<String> listAllCategory();

    List<AssetVO> listAssetByCategory(String type);

    List<String> listByLevel(int level);

    Integer numberSumQuery(String maxType);

    Double worthSumQuery(String maxType);

    List<AssetPO> export();

    Page<AssetVO> exportVo();

    List<AssetPO> exportNoReview();

    int insertNew(AssetPO assetPO);


    List<AssetPO> exportByType();

    //资产变更
    List<AssetAddVO> listAllAdd();

    List<AssetAddVO> listAllChange();

    int assetUpdate(String code);//更新审核状态

    void idempotent(String code);

    int ReviewAssetUpdate(AssetUpdatePO assetUpdatePO);//临时存储待审核的数据

    AssetAddVO getAddNew(String code);

    int saveAddNew(AssetPO assetPO);//保存录入审核成功资产
    void updateAddInfo(AssetPO assetPO);//更新录入表

    int deleteAddNew(String code);//删除录入审核失败资产

    AssetUpdateDTO getSubmitAsset(String code);

    int saveSubmitAsset(AssetUpdateDTO assetUpdateDTO);//存储已审核的数据

    void updateChangeInfo(String code);

    int updateChangeOff(String code);

    int updateChangeOffInfo(String code);

    int addUpdate(AssetPO assetPO);

    void addDelete(String code);

    int updateAddOff(String code);

    List<TypeListVO> listAllType();

    Page<AssetVO> pageTest();

    int assetUpdateDeleteInfo(AssetUpdatePO assetUpdatePO);

    AssetVO selectByCode(String code);

    void delete(String code);

    List<AssetPO> download();

    Page<AssetVO> getAsset();

    List<AssetAddVO> listAllAddLog();

    List<AssetAddVO> listAllChangeLog();
}
