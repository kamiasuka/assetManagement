package cn.tedu.asset.server.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * 配置类：设置自动扫描
 */
@Configuration
@MapperScan("cn.tedu.asset.server.dao.persist.mapper")
public class MyBatisConfig {
}
