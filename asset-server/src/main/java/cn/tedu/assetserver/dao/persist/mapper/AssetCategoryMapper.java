package cn.tedu.assetserver.dao.persist.mapper;

import cn.tedu.assetserver.pojo.entity.AssetCategory;
import cn.tedu.assetserver.pojo.vo.AssetCategoryVO;
import cn.tedu.assetserver.pojo.vo.CategoryStandardVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AssetCategoryMapper {

    List<AssetCategoryVO> selectlistAll();
    int selectCategoryByname(String name);
    AssetCategory selectCategoryById(int parentId);
    int updateParent(AssetCategory parentCategory);
    int insertCategory(AssetCategory assetCategory);

    CategoryStandardVO getStandardById(Integer id);
    int countByNameAndNotId(@Param("id") Integer id, @Param("name") String name);
    int updateCategory(AssetCategory assetCategory);

    int selectAssetByType(String type);
    int deleteCategory(int id);


}
