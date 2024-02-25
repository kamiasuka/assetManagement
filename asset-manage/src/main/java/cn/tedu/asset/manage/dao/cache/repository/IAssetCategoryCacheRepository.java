package cn.tedu.asset.manage.dao.cache.repository;

import cn.tedu.asset.manage.pojo.vo.AssetCategoryVO;

import java.util.List;

public interface IAssetCategoryCacheRepository {
    List<AssetCategoryVO> listByCategory();
}
