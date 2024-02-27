package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.pojo.param.AssetInfoAddParam;
import cn.tedu.asset.manage.service.IAssetInfoService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/v1/asset-info")
public class AssetInfoController {

    @Autowired
    private IAssetInfoService assetInfoService;

    public AssetInfoController(){log.info("创建控制器对象：AssetInfoController");}

    @PostMapping("/add-new")
    public JsonResult addNew(AssetInfoAddParam assetInfoAddParam){
        log.debug("开始处理【资产录入】的操作，参数：{}",assetInfoAddParam);
        assetInfoService.addNew(assetInfoAddParam);
        return JsonResult.ok();
    }

}
