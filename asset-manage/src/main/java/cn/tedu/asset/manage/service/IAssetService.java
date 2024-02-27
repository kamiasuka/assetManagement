package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.vo.AssetVO;

import java.util.List;

public interface IAssetService {

    List<AssetVO> getAssetByType(String type);

    void rebuildCache();

    AssetVO getAssetByES();
}
