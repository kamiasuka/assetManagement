package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetChangeDTO;
import cn.tedu.asset.manage.pojo.dto.AssetStatisticDTO;
import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;

import java.util.List;

public interface IAssetService {
    PageData<AssetVO> getAsset(Integer pageNum);

    PageData<AssetVO> getAssetByType(String type,Integer pageNum);

    void rebuildCache();

    List<AssetStatisticDTO> getStatistics();
    AssetVO searchAsset(String code);

    void assetChange(AssetChangeDTO assetChangeDTO);
    int addNew(AssetAddDTO assetAddDTO);

    void assetDelete(String code);

}
