package cn.tedu.assetserver.service.impl;

import cn.tedu.assetcommom.ex.ServiceException;
import cn.tedu.assetcommom.response.StatusCode;
import cn.tedu.assetserver.mapper.AssetCategoryMapper;
import cn.tedu.assetserver.pojo.dto.CategoryAddDTO;
import cn.tedu.assetserver.pojo.dto.CategoryUpdateDTO;
import cn.tedu.assetserver.pojo.entity.AssetCategory;
import cn.tedu.assetserver.pojo.vo.AssetCategoryVO;
import cn.tedu.assetserver.pojo.vo.CategoryStandardVO;
import cn.tedu.assetserver.service.IAssetCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class AssetCategoryServiceImpl implements IAssetCategoryService {
    @Autowired(required = false)
    private AssetCategoryMapper categoryMapper;

    @Override
    public List<AssetCategoryVO> listAll() {
        return categoryMapper.selectlistAll();
    }

    /**
     * 添加资产分类
     * @param categoryDTO
     */
    @Override
    public void addCategory(CategoryAddDTO categoryDTO) {
        log.debug("开始处理【添加类别】的业务，参数：{}", categoryDTO);
        String name = categoryDTO.getName();
        /** 查询是否存在同名分类 */
        int num = categoryMapper.selectCategoryByname(name);
        log.debug("根据名称【{}】统计数量，结果：{}", name, num);
        if (num>=1){
            String msg = "添加失败，此类别已经被占用";
            log.warn(msg);
            throw new ServiceException(StatusCode.OPERATION_FAILED,msg);
        }

        AssetCategory a = categoryMapper.selectCategoryById(categoryDTO.getParentId());
        System.out.println("子级深度"+a.getLevel());
        if(a.getLevel()>=3){
            String msg = "添加失败，最多允许添加三个子分类";
            log.warn(msg);
            throw new ServiceException(StatusCode.OPERATION_FAILED,msg);
        }

        /** 判断插入的是子级还是父级，记录深度 */
        int parentId = categoryDTO.getParentId();
        int depth = 1;
        AssetCategory parentCategory = null;
        if (parentId != 0) {
            parentCategory = categoryMapper.selectCategoryById(parentId);
            if (parentCategory == null){
                String msg = "添加失败，父级类别不存在";
                log.warn(msg);
                throw new ServiceException(StatusCode.OPERATION_FAILED,msg);
            }else {
                depth = parentCategory.getLevel()+1;
            }
        }

        /** 添加父级，更新深度和isParent */
        AssetCategory assetCategory = new AssetCategory();
        BeanUtils.copyProperties(categoryDTO,assetCategory);
        assetCategory.setLevel(depth);
        assetCategory.setIsParent(0);
        int rows = categoryMapper.insertCategory(assetCategory);
        if (rows != 1) {
            String message = "添加类别失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED, message);
        }
        System.err.println(assetCategory);
        /** 更新父类 */
        if (parentId != 0 && parentCategory.getIsParent()==0){
            AssetCategory updateParent = new AssetCategory();
            updateParent.setId(parentId);
            updateParent.setIsParent(1);
            rows = categoryMapper.updateParent(updateParent);
            if (rows != 1){
                String message = "添加类别失败，服务器忙，请稍后再尝试！";
                log.warn(message);
                throw new ServiceException(StatusCode.OPERATION_FAILED, message);
            }
        }
    }

    /**
     * 修改资产分类
     * @param categoryUpdateDTO
     */
    @Override
    public void updateCategory(Integer categoryId, CategoryUpdateDTO categoryUpdateDTO) {
        log.debug("开始处理【修改类别】的业务，id:" +categoryId +", 参数：{}", categoryUpdateDTO);

        CategoryStandardVO queryResult = categoryMapper.getStandardById(categoryId);
        if (queryResult==null){
            String message = "修改类别失败，尝试修改的类别数据不存在！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED, message);
        }

        int count = categoryMapper.countByNameAndNotId( categoryId,categoryUpdateDTO.getName());
//        log.debug("执行【统计匹配名称但不匹配ID的类别的数量】的数据访问，ID：{}，类别：{}", categoryId, categoryUpdateDTO.getName());
        log.debug("执行【统计匹配名称数量】的数据访问，ID：{}，类别：{}", categoryId, categoryUpdateDTO.getName());
        if (count>0){
            String message = "修改类别失败，该类别名称已被占用！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED, message);
        }

        AssetCategory assetCategory = new AssetCategory();
        BeanUtils.copyProperties(categoryUpdateDTO,assetCategory);
        assetCategory.setId(categoryId);
        int num = categoryMapper.updateCategory(assetCategory);
        if (num!=1){
            String message = "修改类别详情失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED, message);
        }
    }

    /**
     * 删除资产分类
     * @param id
     */
    @Override
    public void deleteCategory(Integer id) {
        log.debug("开始处理【根据ID删除类别】的业务，参数：{}", id);

        CategoryStandardVO queryResult = categoryMapper.getStandardById(id);
        if (queryResult==null){
            String message = "删除类别失败，该分类不存在！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED,message);
        }

        if (queryResult.getParentId()==1){
            String message = "删除类别失败，该分类存在子分类！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED,message);
        }

        int count = categoryMapper.selectAssetByType(queryResult.getType());
        if (count>0){
            String message = "删除类别失败，该分类下还有资产关联！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED,message);

        }

        int num = categoryMapper.deleteCategory(id);
        if (num!=1){
            String message = "删除类别失败，服务器忙，请稍后再尝试！";
            log.warn(message);
            throw new ServiceException(StatusCode.OPERATION_FAILED,message);

        }
    }
}
