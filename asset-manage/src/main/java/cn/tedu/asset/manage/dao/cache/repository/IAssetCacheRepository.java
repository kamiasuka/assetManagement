package cn.tedu.asset.manage.dao.cache.repository;

import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.po.AssetUpdatePO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import com.github.pagehelper.Page;

import java.io.Serializable;
import java.util.List;

public interface IAssetCacheRepository {
    void deleteAll();

    void saveCategory(String type);

    void saveAsset(String type,List<AssetVO> assetVOList);
    void saveAll();

    List<PageData> listByAsset(String type);

    void updateCache(AssetUpdatePO assetUpdatePO);

    PageData<AssetVO> listAll(int page);

}
