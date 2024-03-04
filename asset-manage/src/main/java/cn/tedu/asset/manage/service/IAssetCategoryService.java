package cn.tedu.asset.manage.service;

import cn.tedu.asset.manage.pojo.dto.CategoryAddDTO;
import cn.tedu.asset.manage.pojo.dto.CategoryUpdateDTO;
import cn.tedu.asset.manage.pojo.vo.AssetCategoryVO;

import java.util.List;

public interface IAssetCategoryService {
    //List<AssetCategoryVO> getCategoryOne();
    List<AssetCategoryVO> getAllCategory();

    void addCategory(CategoryAddDTO categoryDTO);

    void updateCategory(Integer id, CategoryUpdateDTO categoryUpdateDTO);

    void deleteCategory(Integer id);

}
