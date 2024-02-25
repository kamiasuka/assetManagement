package cn.tedu.asset.manage.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：设置自动扫描
 */
@Configuration
@MapperScan("cn.tedu.asset.manage.dao.persist.mapper")
public class MyBatisConfig {
}
