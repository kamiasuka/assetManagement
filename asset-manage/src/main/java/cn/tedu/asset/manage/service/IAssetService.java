package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetStatisticDTO;
import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;

import java.util.List;

public interface IAssetService {

    PageData<AssetVO> getAssetByType(String type,Integer pageNum);

    void rebuildCache();

    List<AssetStatisticDTO> getStatistics();
    AssetVO searchAsset(String code);

    void addNew(AssetAddDTO assetAddDTO);

    void assetUpdate(AssetUpdateDTO assetUpdateDTO);
    int addNew(AssetAddDTO assetAddDTO);
}
