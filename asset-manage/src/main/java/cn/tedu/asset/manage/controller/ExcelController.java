package cn.tedu.asset.manage.controller;

import cn.tedu.asset.manage.service.IExcelService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;


@RequestMapping("/v1/asset")
@RestController
@Api("Excel报表导出")
public class ExcelController {
    @Autowired
    private IExcelService iExcelService;
    @GetMapping("/download")
    public void ExportExcel(HttpServletResponse response){
        iExcelService.export(response);
    }
}

