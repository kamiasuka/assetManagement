package cn.tedu.asset.manage.dao.persist.mapper;

import cn.tedu.asset.manage.pojo.entity.AssetCategory;
import cn.tedu.asset.manage.pojo.vo.AssetCategoryVO;
import cn.tedu.asset.manage.pojo.vo.AssetMaxCategoryVO;
import cn.tedu.asset.manage.pojo.vo.CategoryStandardVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetCategoryMapper {

    List<AssetCategoryVO> selectListAll();

    int selectCategoryByName(String name);

    AssetCategory selectCategoryById(int parentId);
    int updateParent(AssetCategory parentCategory);
    int insertCategory(AssetCategory assetCategory);

    CategoryStandardVO getStandardById(Integer id);
    int countByNameAndNotId(@Param("id") Integer id, @Param("name") String name);
    int updateCategory(AssetCategory assetCategory);

    int selectAssetByName(String name);
    int deleteCategory(int id);

    int countCategory(int parentId);
    int updateIsParent(int parentId);


    List<AssetCategoryVO> listByCategoryOne();

    List<AssetMaxCategoryVO> selectMaxCategory();
}
