package cn.tedu.asset.manage;

import cn.tedu.asset.manage.dao.persist.mapper.AssetMapper;
import cn.tedu.asset.manage.pojo.po.AssetPO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AssetServerApplicationTests {

    @Autowired
    private AssetMapper assetMapper;

    @Test
    void getAssetByType() {
        List<AssetPO> assetPOList = assetMapper.listAssetByCategory("办公用品");
        System.out.println(assetPOList.toString());
    }

}
