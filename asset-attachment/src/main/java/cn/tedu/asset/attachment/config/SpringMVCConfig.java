package cn.tedu.asset.attachment.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class SpringMVCConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  //代表客户端请求的任意资源
                .allowedHeaders("*")    //带任意请求头都允许访问
                .allowedMethods("*")    //任意请求方式都允许访问
                .allowedOriginPatterns("*") //任意主机地址和端口号都允许访问
                .allowCredentials(true) //允许携带cookie/session相关信息
                .maxAge(3600);  //一小时内同一请求直接放行
    }
}
