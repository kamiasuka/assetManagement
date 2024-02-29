package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.commom.response.StatusCode;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.entity.AssetExcelData;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.service.IExcelService;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import javax.sql.rowset.serial.SerialException;
import java.io.IOException;
import java.net.URLEncoder;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements IExcelService {
    @Autowired
    private AssetMapper assetMapper;

    @Override
    public void export(HttpServletResponse response) {

        List<AssetPO> assetPOList = assetMapper.export();
        List<AssetExcelData> excelDataList = new ArrayList<>();

        for (AssetPO assetPO : assetPOList) {
            AssetExcelData excelData = new AssetExcelData();
            BeanUtils.copyProperties(assetPO, excelData);
            excelDataList.add(excelData);
        }
        try {
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("资产报表", "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), AssetExcelData.class).sheet("asset").doWrite(excelDataList);
        } catch (IOException e) {
            throw new ServiceException(StatusCode.OPERATION_FAILED,"资产报表下载失败");
        }
    }
}
