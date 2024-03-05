package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.service.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/assetReview")
public class ReviewController {
    @Autowired(required = false)
    private AssetMapper assetMapper;
    @Autowired
    private IReviewService iReviewService;

    /**
     * 展示录入的资产
     * @return
     */
    @GetMapping("listAllAdd")
    public JsonResult listAllAdd(){
        AssetVO assetVO = iReviewService.listAllAdd();
        return JsonResult.ok(assetVO);
    }

    /**
     * 展示变更的资产
     * @return
     */
    @GetMapping("listAllChange")
    public JsonResult listAllChange(){
        AssetVO assetVO = iReviewService.listAllChange();
        return JsonResult.ok(assetVO);
    }

    /**
     * 录入审核通过
     */
    @GetMapping("addNewNo/{code}")
    public JsonResult addNewNo(@PathVariable String code){
        iReviewService.addNewOn(code);
        return JsonResult.ok();
    }

    /**
     * 录入审核不通过
     */
    @GetMapping("addNewOff/{code}")
    public JsonResult addNewOff(@PathVariable String code){
        iReviewService.addNewOff(code);
        return JsonResult.ok();
    }

    /**
     * 变更审核通过
     */
    @GetMapping("changeOn/{code}")
    public JsonResult changeOn(@PathVariable String code){
        iReviewService.changeOn(code);
        return JsonResult.ok();
    }

    /**
     * 变更审核通过
     */
    @GetMapping("changeOff/{code}")
    public JsonResult changeOff(@PathVariable String code){
        iReviewService.changeOff(code);
        return JsonResult.ok();
    }
}
