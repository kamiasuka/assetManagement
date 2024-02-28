package cn.tedu.asset.manage.service;

import javax.servlet.http.HttpServletResponse;

public interface IExcelService {
    void exportDataToExcel(HttpServletResponse response, String fileName);
}
