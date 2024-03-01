package cn.tedu.asset.manage.Util;

public class ExcelUtil {

    public static String getPath(){
        return ExcelUtil.class.getResource("/").getPath() ;
    }
}
