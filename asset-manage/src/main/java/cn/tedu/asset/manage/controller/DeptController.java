package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.pojo.dto.AssetAddDTO;
import cn.tedu.asset.manage.pojo.dto.AssetUpdateDTO;
import cn.tedu.asset.manage.pojo.dto.DeptAddDTO;
import cn.tedu.asset.manage.pojo.dto.DeptUpdateDTO;
import cn.tedu.asset.manage.pojo.vo.DeptVO;
import cn.tedu.asset.manage.service.IDeptService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/v1/dept")
@Api(tags = "部门管理")
public class DeptController {

    @Autowired
    private IDeptService deptService;

    @GetMapping("/getDeptByCode/{code}")
    @ApiOperation("1.根据资产编号code查找部门")
    @ApiOperationSupport(order = 50)
    public JsonResult getDeptByCode(@PathVariable String code){
        log.debug("开始处理【根据资产编号code查找部门】的操作");
        DeptVO deptVO  = deptService.getDeptByCode(code);
        return JsonResult.ok(deptVO);
    }

    @GetMapping("/listAll")
    @ApiOperation("1.显示所有部门")
    @ApiOperationSupport(order = 100)
    public JsonResult listAllDept(){
        log.debug("开始处理【获取所有部门】的操作");
        List<DeptVO> list  = deptService.listAll();
        return JsonResult.ok(list);
    }

    @PostMapping("/add-new")
    @ApiOperation("2.部门新增")
    @ApiOperationSupport(order = 200)
    public JsonResult addNew(DeptAddDTO deptAddDTO){
        log.debug("开始处理【部门新增】的操作，参数：{}",deptAddDTO);
        int count = deptService.addNew(deptAddDTO);
        return JsonResult.ok(count);
    }

    /**
     * 资产变更
     */
    @PostMapping("/update")
    @ApiOperation("3.部门修改")
    @ApiOperationSupport(order = 300)
    public JsonResult deptUpdate(DeptUpdateDTO deptUpdateDTO){
        log.debug("开始处理【部门修改】的请求，参数：{}",deptUpdateDTO);
        deptService.deptUpdate(deptUpdateDTO);
        return JsonResult.ok();
    }

    /**
     * 资产删除
     */
    @GetMapping("/delete/{code}")
    @ApiOperation("4.部门删除")
    @ApiOperationSupport(order = 400)
    public JsonResult deptDelete(@PathVariable String code){
        log.debug("开始处理【部门删除】的请求");
        int count = deptService.deptDelete(code);
        return JsonResult.ok();
    }
}
