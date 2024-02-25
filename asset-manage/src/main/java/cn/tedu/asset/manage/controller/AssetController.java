package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.pojo.vo.AssetCategoryVO;
import cn.tedu.asset.manage.service.IAssetService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/asset")
public class AssetController {
    @Autowired
    private IAssetService iAssetService;

    @GetMapping("getAsset/{type}")
    @ApiOperation("显示所有资产")
    public JsonResult getAssetByType(@PathVariable String type){
        log.debug("开始处理【显示所有资产】的请求");

        return JsonResult.ok();
    }

}
