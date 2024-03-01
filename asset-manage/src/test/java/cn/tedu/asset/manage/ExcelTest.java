package cn.tedu.asset.manage;

import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.Util.ExcelUtil;
import cn.tedu.asset.manage.pojo.entity.AssetExcelData;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import com.alibaba.excel.EasyExcel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ExcelTest {
    @Autowired
    private AssetMapper assetMapper;

    @Test
    void test() {
        List<AssetPO> assetPOList = assetMapper.export();

        List<AssetExcelData> excelDataList = new ArrayList<>();
        AssetExcelData excelData = new AssetExcelData();

        for (AssetPO assetPO : assetPOList) {
            BeanUtils.copyProperties(assetPO, excelData);
            excelDataList.add(excelData);
        }

        String fileName = ExcelUtil.getPath() + "资产数据2" + ".xlsx";
        EasyExcel.write(fileName, AssetExcelData.class).sheet("资产数据").doWrite(excelDataList);
    }
}

