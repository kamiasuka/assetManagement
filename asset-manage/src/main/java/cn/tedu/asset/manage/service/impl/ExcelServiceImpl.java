package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.entity.AssetExcelData;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.service.IExcelService;
import com.alibaba.excel.EasyExcel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements IExcelService {
    @Autowired
    private AssetMapper assetMapper;

    @Override
    public void exportDataToExcel(HttpServletResponse response,String fileName) {
        List<AssetPO> assetPOList = assetMapper.listAssetByCategory("办公用品");
        List<AssetExcelData> dataList = new ArrayList<>();
        BeanUtils.copyProperties(assetPOList,dataList);
        try {
            EasyExcel.write(response.getOutputStream(), AssetExcelData.class).sheet("资产信息").doWrite(dataList);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
