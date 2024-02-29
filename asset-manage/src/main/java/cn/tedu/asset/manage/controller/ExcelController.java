package cn.tedu.asset.manage.controller;

import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.excel.ExcelUtil;
import cn.tedu.asset.manage.pojo.entity.AssetExcelData;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.service.IExcelService;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/v1/asset")
@RestController
public class ExcelController {
    @Autowired
    private IExcelService iExcelService;
    @Autowired
    private AssetMapper assetMapper;

    @GetMapping("/export")
    public void ExportExcel(/*HttpServletResponse response*/) throws IOException {

/*
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("资产报表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
*/
        //iExcelService.exportDataToExcel(response, fileName);

        List<AssetPO> assetPOList = assetMapper.listAssetByCategory("办公用品");

        List<AssetExcelData> excelDataList = new ArrayList<>();
        AssetExcelData excelData = new AssetExcelData();

        for (AssetPO assetPO:assetPOList){
            BeanUtils.copyProperties(assetPO,excelData);
            excelDataList.add(excelData);
        }

        String fileName = ExcelUtil.getPath()+"测试"+".xlsx";
        EasyExcel.write(fileName,AssetPO.class).sheet("资产数据").doWrite(excelDataList);

    }
}
