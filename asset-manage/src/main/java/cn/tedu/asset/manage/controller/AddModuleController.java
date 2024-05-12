package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.pojo.dto.AssetLeaseSubmitSDTO;
import cn.tedu.asset.manage.pojo.dto.AssetSaleSubmitSDTO;
import cn.tedu.asset.manage.pojo.dto.ContractDTO;
import cn.tedu.asset.manage.pojo.entity.AssetLease;
import cn.tedu.asset.manage.pojo.entity.AssetSale;
import cn.tedu.asset.manage.pojo.vo.*;
import cn.tedu.asset.manage.service.AddModuleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("v1/module")
public class AddModuleController {
    @Autowired
    private AddModuleService addModuleService;

    /**
     * 资产变卖
     */
    @GetMapping("listAllAsset/{page}")
    @ApiOperation("显示所有资产")
    public JsonResult listAllAsset(@PathVariable Integer page) {
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetVOO> pageData = addModuleService.getAsset(pageNum);
        return JsonResult.ok(pageData);
    }

    //获取记录
    @GetMapping("{page}/getSaleAsset")
    public JsonResult getSaleAsset(@PathVariable Integer page) {
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetSaleVO> pageData = addModuleService.getSaleAsset(pageNum);
        return JsonResult.ok(pageData);
    }

    //提交变更申请
    @PostMapping("submit")
    public JsonResult submitSaleAsset(AssetSaleSubmitSDTO assetSaleSubmitSDTO) {
        System.out.println("变卖：" + assetSaleSubmitSDTO);
        addModuleService.submitSaleAsset(assetSaleSubmitSDTO);
        return JsonResult.ok();
    }

    //审核变卖资产
    @GetMapping("{page}/getSalePostAsset")
    public JsonResult getSalePostAsset(@PathVariable Integer page) {
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetSaleVO> pageData = addModuleService.getSalePostAsset(pageNum);
        return JsonResult.ok(pageData);
    }

    //变卖审核通过
    @GetMapping("saleOn/{code}")
    @ApiOperation("录入审核通过")
    public JsonResult saleOn(@PathVariable String code) {
        addModuleService.saleOn(code);
        return JsonResult.ok();
    }

    //变卖审核不通过
    @GetMapping("saleOff/{code}")
    @ApiOperation("录入审核不通过")
    public JsonResult saleOff(@PathVariable String code) {
        addModuleService.saleOff(code);
        return JsonResult.ok();
    }

    /**
     * 资产租借
     *
     * @param page
     * @return
     */
    @GetMapping("{page}/getLeaseAsset")
    public JsonResult getLeaseAsset(@PathVariable Integer page) {
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetLease> pageData = addModuleService.getLeaseAsset(pageNum);
        return JsonResult.ok(pageData);
    }

    //提交租借申请
    @PostMapping("submitlease")
    public JsonResult submitlease(AssetLeaseSubmitSDTO assetLeaseSubmitSDTO) {
        System.out.println("变卖：" + assetLeaseSubmitSDTO);
        addModuleService.submitLeaseAsset(assetLeaseSubmitSDTO);
        return JsonResult.ok();
    }

    //获取租借审核列表
    @GetMapping("{page}/getPostLeaseAsset")
    public JsonResult getPostLeaseAsset(@PathVariable Integer page) {
        Integer pageNum = page == null ? 1 : page;
        PageData<AssetLease> pageData = addModuleService.getPostLeaseAsset(pageNum);
        return JsonResult.ok(pageData);
    }


    //租借审核通过
    @GetMapping("leaseOn/{code}")
    @ApiOperation("录入审核通过")
    public JsonResult leaseOn(@PathVariable String code) {
        addModuleService.leaseOn(code);
        return JsonResult.ok();
    }

    //租借审核不通过
    @GetMapping("leaseOff/{code}")
    @ApiOperation("录入审核不通过")
    public JsonResult leaseOff(@PathVariable String code) {
        addModuleService.leaseOff(code);
        return JsonResult.ok();
    }

    /**
     * 合同管理
     *
     * @param page
     * @return
     */
    @GetMapping("{page}/getContractSale")
    public JsonResult getContractSale(@PathVariable Integer page) {
        Integer pageNum = page == null ? 1 : page;
        PageData<ContractVO> pageData = addModuleService.getContractSale(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("{page}/getContractLease")
    public JsonResult getContractLease(@PathVariable Integer page) {
        Integer pageNum = page == null ? 1 : page;
        PageData<ContractVO> pageData = addModuleService.getContractLease(pageNum);
        return JsonResult.ok(pageData);
    }

    @PostMapping("submitContract")
    public JsonResult submitContract(ContractDTO contractDTO) {
        addModuleService.submitContract(contractDTO);
        return JsonResult.ok();
    }

    @GetMapping("{page}/income")
    public JsonResult income(@PathVariable Integer page) {
        Integer pageNum = page == null ? 1 : page;
        PageData<InComeVO> pageData = addModuleService.income(pageNum);
        return JsonResult.ok(pageData);
    }
}
