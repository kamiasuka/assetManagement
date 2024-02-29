package cn.tedu.asset.manage.service;

import cn.tedu.asset.commom.response.JsonResult;
import cn.tedu.asset.manage.pojo.entity.AssetExcelData;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IExcelService {
    void export(HttpServletResponse response);
}
