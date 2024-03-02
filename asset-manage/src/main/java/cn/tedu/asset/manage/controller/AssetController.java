package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetStatisticDTO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import cn.tedu.asset.manage.service.IAssetService;
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

    @GetMapping("getAsset/{type}/{page}")
    @ApiOperation("根据分类显示资产")
    public JsonResult getAssetByType(@PathVariable String type,@PathVariable Integer page){
        log.debug("开始处理【根据分类加载资产】的请求，参数：{},页码：{}",type,page);
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetVO> pageData = iAssetService.getAssetByType(type,pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("/getStatistics")
    @ApiOperation("显示统计信息")
    public JsonResult getStatistics(){
        List<AssetStatisticDTO> list = iAssetService.getStatistics();
        return JsonResult.ok(list);
    }

    @GetMapping("/search/{keyword}")
    @ApiOperation("资产搜索")
    public JsonResult searchAssetByES(
            @PathVariable
            @Pattern(regexp = "^(?! )\\S{1,20}(?<! )$", message = "关键词必须是1~20个字符，且首尾不可以是空格！")String keyword){
        log.debug("开始处理【资产搜索】的请求,关键词：{}",keyword);
        iAssetService.searchAssetByES(keyword);
        return JsonResult.ok();
    }

    @PostMapping("/add-new")
    @ApiOperation("资产录入")
    public JsonResult addNew(AssetAddDTO assetAddDTO){
        log.debug("开始处理【资产录入】的操作，参数：{}",assetAddDTO);
        iAssetService.addNew(assetAddDTO);
        return JsonResult.ok();
    }
}
