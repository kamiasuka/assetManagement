package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetStatisticDTO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;

import java.util.List;

public interface IAssetService {

    List<AssetVO> getAssetByType(String type);

    void rebuildCache();

    List<AssetStatisticDTO> getStatistics();
    AssetVO getAssetByES(String code);

    void addNew(AssetAddDTO assetAddDTO);
}
