package cn.tedu.asset.manage.startup;

import cn.tedu.asset.manage.service.IAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Repository;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Slf4j
@Repository
public class AssetCacheRunner implements ApplicationRunner {
    @Autowired
    private IAssetService iAssetService;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.debug("开始执行【重建资产数据】的数据预热");
        iAssetService.rebuildCache();
        final Runnable task = new Runnable() {
            public void run() {
                iAssetService.rebuildCache();
            }
        };
        // 设定周期性执行任务，首次延迟为0，周期为10小时
        scheduler.scheduleAtFixedRate(task, 0, 10, TimeUnit.HOURS);
    }
}
