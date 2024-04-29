package cn.tedu.asset.manage.startup;

import cn.tedu.asset.manage.service.IAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class AssetCacheRunner implements ApplicationRunner {
    @Autowired
    private IAssetService iAssetService;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("开始执行【重建资产数据】的数据预热");
        iAssetService.rebuildCache();
    }
}
