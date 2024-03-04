package cn.tedu.asset.manage.controller;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import cn.tedu.asset.manage.service.IExcelService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


@RequestMapping("/v1/excel")
@RestController
@Api(tags = "Excel报表导出")
public class ExcelController {
    @Autowired
    private IExcelService iExcelService;


    @GetMapping("listAll/{pageNum}")
    @ApiOperation("展示报表")
    public JsonResult listAll(@PathVariable Integer pageNum){
        PageData<AssetVO> pageData = iExcelService.pageListAll(pageNum);
        return JsonResult.ok(pageData);
    }

    @GetMapping("listAllByType")
    @ApiOperation("根据分类展示报表")
    public JsonResult listAllByType(){
        List<AssetVO> list = iExcelService.listAll();
        return JsonResult.ok(list);
    }

    @GetMapping("download")
    @ApiOperation("下载报表")
    public void ExportExcel(HttpServletResponse response){
        iExcelService.export(response);
    }

    @GetMapping("exportByType")
    @ApiOperation("根据分类下载报表")
    public void downloadByType(HttpServletResponse response){
        iExcelService.exportByType(response);
    }


}

