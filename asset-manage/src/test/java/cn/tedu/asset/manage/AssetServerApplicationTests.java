package cn.tedu.asset.manage;

import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.service.IAssetService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AssetServerApplicationTests {

    @Autowired
    private AssetMapper assetMapper;

    @Autowired
    private IAssetService iAssetService;
    @Test
    void getAssetByType() {
        List<AssetPO> assetPOList = assetMapper.listAssetByCategory("办公用品");
        System.out.println(assetPOList.toString());
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
