package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.dao.cache.repository.IAssetCacheRepository;
import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetChangeDTO;
import cn.tedu.asset.manage.pojo.dto.AssetStatisticDTO;
import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import cn.tedu.asset.manage.service.IAssetService;
import cn.tedu.asset.manage.service.IExcelService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Pattern;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/asset")
@Api(tags = "资产管理")
public class AssetController {
    @Autowired
    private IAssetService iAssetService;
    @Autowired
    private IExcelService iExcelService;
    @Autowired
    private IAssetCacheRepository iAssetCacheRepository;

    /**
     * 显示所有资产(审核通过)
     */
    @GetMapping("listAllAsset/{page}")
    @ApiOperation("显示所有资产")
    public JsonResult listAllAsset(@PathVariable Integer page){
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetVO> pageData = iExcelService.pageListAll(pageNum);
        return JsonResult.ok(pageData);
    }

    /**
     * 显示所有资产(未审核)
     */
    @GetMapping("listAllNoReview")
    @ApiOperation("未审核资产（审核员用）")
    public JsonResult listAllNoReview(){
        List<AssetVO> list = iExcelService.listAllNoReview();
        return JsonResult.ok(list);
    }


    /**
     * 根据分类显示资产
     */
    @GetMapping("getAsset/{type}&{page}")
    @ApiOperation("根据分类显示资产")
    public JsonResult getAssetByType(@PathVariable String type,@PathVariable Integer page){
        log.debug("开始处理【根据分类加载资产】的请求，参数：{},页码：{}",type,page);
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetVO> pageData = iAssetService.getAssetByType(type,pageNum);
        return JsonResult.ok(pageData);
    }

    /**
     * 显示统计信息
     */
    @GetMapping("getStatistics")
    @ApiOperation("显示统计信息")
    public JsonResult getStatistics(){
        List<AssetStatisticDTO> list = iAssetService.getStatistics();
        return JsonResult.ok(list);
    }

    /**
     * 资产搜索
     */
    @GetMapping("search/{keyword}")
    @ApiOperation("1.资产搜索")
    public JsonResult searchAsset(
            @PathVariable
            @Pattern(regexp = "^(?! )\\S{1,20}(?<! )$", message = "关键词必须是1~20个字符，且首尾不可以是空格！")String keyword){
        log.debug("开始处理【资产搜索】的请求,关键词：{}",keyword);
        iAssetService.searchAsset(keyword);
        return JsonResult.ok();
    }

    @PostMapping("add-new")
    @ApiOperation("2.资产录入")
    public JsonResult addNew(AssetAddDTO assetAddDTO){
        log.debug("开始处理【资产录入】的操作，参数：{}",assetAddDTO);
        int count = iAssetService.addNew(assetAddDTO);
        return JsonResult.ok(count);
    }

    /**
     * 资产变更
     */
    @PostMapping("update")
    @ApiOperation("3.资产变更")
    public JsonResult assetUpdate(AssetChangeDTO assetChangeDTO){
        log.debug("开始处理【资产变更】的请求，参数：{}",assetChangeDTO);
        iAssetService.assetChange(assetChangeDTO);
        return JsonResult.ok();
    }

    /**
     * 资产删除
     */
    @GetMapping("delete/{code}")
    @ApiOperation("4.资产删除")
    @ApiOperationSupport(order = 400)
    public JsonResult assetDelete(@PathVariable String code){
        log.debug("开始处理【资产删除】的请求");
        iAssetService.assetDelete(code);
        return JsonResult.ok();
    }

    /**
     * 资产批量删除
     */
    @PostMapping("delete")
    @ApiOperation("5.资产批量删除")
    @ApiOperationSupport(order = 500)
    public JsonResult assetDeleteList(){
        log.debug("开始处理【资产批量删除】的请求");

        return JsonResult.ok();
    }

    @GetMapping("Refresh")
    @ApiOperation("6.数据刷新")
    public JsonResult Refresh(){
        log.debug("开始处理【数据刷新】的请求");
        iAssetService.rebuildCache();
        return JsonResult.ok();
    }
}
