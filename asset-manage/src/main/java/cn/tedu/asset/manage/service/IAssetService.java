package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.dto.*;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;

import java.util.List;

public interface IAssetService {
    PageData<AssetVO> getAsset(Integer pageNum);

    PageData<AssetVO> getAssetByType(String type,Integer pageNum);

    void rebuildCache();

    List<AssetStatisticDTO> getStatistics();
    AssetVO searchAsset(AssetSearchDTO assetSearchDTO);

    void assetChange(AssetChangeDTO assetChangeDTO);
    int addNew(AssetAddDTO assetAddDTO);

    void assetDelete(String code);

    /**
     * 更新asset_add_info
     * @param addUpdateDTO
     * @return
     */
    void addUpdate(AddUpdateDTO addUpdateDTO);

    void addDelete(String code);
}
