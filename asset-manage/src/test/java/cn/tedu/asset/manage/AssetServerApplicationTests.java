package cn.tedu.asset.manage;

import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.service.IAssetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class AssetServerApplicationTests {
    @Autowired
    private IAssetService iAssetService;
    @Autowired
    private AssetMapper assetMapper;
    @Test
    void contextLoads() {

    }

    /**
     * 获取所有分类的列表
     */
    @Test
    public void listByLevel(){

        System.out.println(assetMapper.listByLevel(1));
    }

    /**
     * 得到首页的统计信息
     */
    @Test
    public void getStatistics(){
        iAssetService.getStatistics();
    }

}
