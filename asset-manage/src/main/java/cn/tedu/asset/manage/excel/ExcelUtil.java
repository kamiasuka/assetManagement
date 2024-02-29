package cn.tedu.asset.manage.excel;

public class ExcelUtil {

    public static String getPath(){
        return ExcelUtil.class.getResource("/").getPath() ;
    }
    public static void main(String[] args) {
        System.out.println(getPath());
    }
}
