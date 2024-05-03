package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.vo.AssetAddVO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import cn.tedu.asset.manage.service.IReviewService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/assetReview")
@Api(tags = "资产审核")
public class ReviewController {
    @Autowired(required = false)
    private AssetMapper assetMapper;
    @Autowired
    private IReviewService iReviewService;

    /**
     * 展示录入的资产
     * @return
     */
    @GetMapping("listAllAdd/{page}")
    @ApiOperation("展示所有录入资产")
    public JsonResult listAllAdd(@PathVariable Integer page){
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetAddVO> pageData = iReviewService.listAllAdd(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("listAllAddLog/{page}")
    @ApiOperation("展示所有录入资产")
    public JsonResult listAllAddLog(@PathVariable Integer page){
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetAddVO> pageData = iReviewService.listAllAddLog(pageNum);
        return JsonResult.ok(pageData);
    }

    /**
     * 展示变更的资产
     * @return
     */
    @GetMapping("listAllChange/{page}")
    @ApiOperation("展示所有变更资产")
    public JsonResult listAllChange(@PathVariable Integer page){
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetAddVO> pageData = iReviewService.listAllChange(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("listAllChangeLog/{page}")
    @ApiOperation("展示所有变更资产")
    public JsonResult listAllChangeLog(@PathVariable Integer page){
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetAddVO> pageData = iReviewService.listAllChangeLog(pageNum);
        return JsonResult.ok(pageData);
    }

    /**
     * 录入审核通过
     */
    @GetMapping("addNewOn/{code}")
    @ApiOperation("录入审核通过")
    public JsonResult addNewOn(@PathVariable String code){
        iReviewService.addNewOn(code);
        return JsonResult.ok();
    }

    /**
     * 录入审核不通过
     */
    @GetMapping("addNewOff/{code}")
    @ApiOperation("录入审核不通过")
    public JsonResult addNewOff(@PathVariable String code){
        iReviewService.addNewOff(code);
        return JsonResult.ok();
    }

    /**
     * 变更审核通过
     */
    @GetMapping("changeOn/{code}")
    @ApiOperation("变更审核通过")
    public JsonResult changeOn(@PathVariable String code){
        iReviewService.changeOn(code);
        return JsonResult.ok();
    }

    /**
     * 变更审核通过
     */
    @GetMapping("changeOff/{code}")
    @ApiOperation("变更审核不通过")
    public JsonResult changeOff(@PathVariable String code){
        iReviewService.changeOff(code);
        return JsonResult.ok();
    }
}
