package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetStatisticDTO;
import cn.tedu.asset.manage.service.IAssetService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/asset")
@Api(tags = "资产管理")
public class AssetController {
    @Autowired
    private IAssetService iAssetService;

    @GetMapping("getAsset/{type}")
    @ApiOperation("根据分类显示资产")
    public JsonResult getAssetByType(@PathVariable String type){
        log.debug("开始处理【根据分类加载资产】的请求，参数："+type);
        return JsonResult.ok(iAssetService.getAssetByType(type));
    }

    @GetMapping()
    @ApiOperation("资产全局查询")
    public JsonResult getAssetByES(String code){
        log.debug("开始处理【资产全局搜索】的请求");
        iAssetService.getAssetByES(code);
        return JsonResult.ok();
    }

    @GetMapping("/getStatistics")
    @ApiOperation("显示统计信息")
    public JsonResult getStatistics(){
        List<AssetStatisticDTO> list = iAssetService.getStatistics();
        return JsonResult.ok(list);
    }

    @PostMapping("/add-new")
    @ApiOperation("资产录入")
    public JsonResult addNew(AssetAddDTO assetAddDTO){
        log.debug("开始处理【资产录入】的操作，参数：{}",assetAddDTO);
        iAssetService.addNew(assetAddDTO);
        return JsonResult.ok();
    }
}
