package cn.tedu.asset.manage.service.impl;

import cn.tedu.asset.commom.ex.ServiceException;
import cn.tedu.asset.commom.response.StatusCode;
import cn.tedu.asset.manage.Util.PageInfoToPageDataConverter;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.entity.AssetExcelData;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.pojo.vo.AssetVO;
import cn.tedu.asset.manage.pojo.vo.PageData;
import cn.tedu.asset.manage.service.IExcelService;
import com.alibaba.excel.EasyExcel;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExcelServiceImpl implements IExcelService {
    @Autowired(required = false)
    private AssetMapper assetMapper;

    @Override
    public List<AssetVO> listAll() {
        List<AssetPO> poList = assetMapper.export();
        List<AssetVO> voList = new ArrayList<>();

        for (AssetPO assetPO : poList) {
            AssetVO assetVO = new AssetVO();
            BeanUtils.copyProperties(assetPO, assetVO);
            voList.add(assetVO);
        }
        return voList;
    }

    @Override
    public PageData<AssetVO> pageListAll(Integer pageNum) {
        PageHelper.startPage(pageNum,10);
        Page<AssetVO> voList = assetMapper.pageExport();
        PageInfo<AssetVO> pageInfo = new PageInfo<>(voList);
        return PageInfoToPageDataConverter.convert(pageInfo);
    }

    @Override
    public List<AssetVO> listAllNoReview() {
        List<AssetPO> poList = assetMapper.exportNoReview();
        List<AssetVO> voList = new ArrayList<>();

        for (AssetPO assetPO : poList) {
            AssetVO assetVO = new AssetVO();
            BeanUtils.copyProperties(assetPO, assetVO);
            voList.add(assetVO);
        }
        return voList;
    }



    @Override
    public void export(HttpServletResponse response) {
        List<AssetPO> assetPOList = assetMapper.export();
        method(response, assetPOList);
    }

    @Override
    public void exportByType(HttpServletResponse response) {
        List<AssetPO> assetPOList = assetMapper.exportByType();
        method(response, assetPOList);

    }

    public void method(HttpServletResponse response, List<AssetPO> assetPOList) {
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
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + "-" + System.currentTimeMillis() + ".xlsx");
            EasyExcel.write(response.getOutputStream(), AssetExcelData.class).sheet("asset").doWrite(excelDataList);
        } catch (IOException e) {
            throw new ServiceException(StatusCode.OPERATION_FAILED, "资产报表下载失败");
        }
    }
}
