package cn.tedu.asset.server.service;

import cn.tedu.asset.server.pojo.dto.CategoryAddDTO;
import cn.tedu.asset.server.pojo.dto.CategoryUpdateDTO;
import cn.tedu.asset.server.pojo.vo.AssetCategoryVO;

import java.util.List;

public interface IAssetCategoryService {
    List<AssetCategoryVO> listAll();

    void addCategory(CategoryAddDTO categoryDTO);

    void updateCategory(Integer id, CategoryUpdateDTO categoryUpdateDTO);

    void deleteCategory(Integer id);
}
