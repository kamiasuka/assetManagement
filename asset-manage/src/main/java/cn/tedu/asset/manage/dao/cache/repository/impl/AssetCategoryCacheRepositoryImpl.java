package cn.tedu.asset.manage.dao.cache.repository.impl;

import cn.tedu.asset.manage.dao.cache.repository.IAssetCategoryCacheRepository;
import cn.tedu.asset.manage.dao.persist.mapper.AssetCategoryMapper;
import cn.tedu.asset.manage.pojo.vo.AssetCategoryVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
@Slf4j
@Repository
public class AssetCategoryCacheRepositoryImpl implements IAssetCategoryCacheRepository {
    @Autowired
    private AssetCategoryMapper assetCategoryMapper;

    @Override
    public List<AssetCategoryVO> listByCategory() {
        return assetCategoryMapper.selectListAll();
    }

}
