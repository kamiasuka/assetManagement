package cn.tedu.assetserver.service;

import cn.tedu.assetserver.pojo.dto.CategoryAddDTO;
import cn.tedu.assetserver.pojo.dto.CategoryUpdateDTO;
import cn.tedu.assetserver.pojo.vo.AssetCategoryVO;

import java.util.List;

public interface IAssetCategoryService {
    List<AssetCategoryVO> listAll();

    void addCategory(CategoryAddDTO categoryDTO);

    void updateCategory(Integer id, CategoryUpdateDTO categoryUpdateDTO);

    void deleteCategory(Integer id);
}
