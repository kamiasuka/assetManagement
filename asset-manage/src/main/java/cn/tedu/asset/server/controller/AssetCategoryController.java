package cn.tedu.asset.server.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.server.pojo.dto.CategoryAddDTO;
import cn.tedu.asset.server.pojo.dto.CategoryUpdateDTO;
import cn.tedu.asset.server.pojo.vo.AssetCategoryVO;
import cn.tedu.asset.server.service.IAssetCategoryService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/asset-category")
@Api(tags = "1. 资产分类管理")
public class AssetCategoryController {
    @Autowired(required = false)
    private IAssetCategoryService categoryService;

    /**
     * 显示所有资产分类
     *
     * @return
     */
    @GetMapping("ListAll")
    @ApiOperation("资产分类展示")
    @ApiOperationSupport(order = 100)
    public JsonResult ListAll() {
        log.debug("开始处理【显示所有资产分类】的请求");
        List<AssetCategoryVO> categoryList = categoryService.listAll();
        return JsonResult.ok(categoryList);
    }

    /**
     * 添加资产分类
     *
     * @param categoryDTO
     * @return
     */
    @ApiOperation("添加资产分类")
    @ApiOperationSupport(order = 200)
    @PostMapping("addCategory")
    public JsonResult addCategory(@RequestBody CategoryAddDTO categoryDTO) {
        log.debug("开始处理【添加资产分类】的请求，参数：{}", categoryDTO);
        categoryService.addCategory(categoryDTO);
        return JsonResult.ok();
    }

    /**
     * 修改资产分类
     *
     * @param categoryUpdateDTO
     * @return
     */
    @ApiOperation("修改资产分类")
    @ApiOperationSupport(order = 300)
    @PostMapping("/{categoryId}/updateCategory")
    public JsonResult updateCategory( @RequestBody CategoryUpdateDTO categoryUpdateDTO, @PathVariable Integer categoryId) {
        log.debug("开始处理【修改资产分类】的请求，参数：{}", categoryUpdateDTO);
        categoryService.updateCategory(categoryId, categoryUpdateDTO);
        return JsonResult.ok();
    }

    /**
     * 删除资产分类
     *
     * @param id
     * @return
     */
    @ApiOperation("删除资产分类")
    @ApiOperationSupport(order = 400)
    @PostMapping("deleteCategory/{id}")
    public JsonResult deleteCategory(@PathVariable Integer id) {
        log.debug("开始处理【修改资产分类】的请求，资产分类id参数：{}", id);
        categoryService.deleteCategory(id);
        return JsonResult.ok();
    }
}
