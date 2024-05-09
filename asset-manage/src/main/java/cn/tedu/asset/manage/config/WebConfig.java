package cn.tedu.asset.manage.config;

import cn.tedu.asset.manage.Interceptor.ManageInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration //配置类
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private ManageInterceptor manageInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(manageInterceptor).addPathPatterns("/**").excludePathPatterns("v1/excel/download/");
    }
}
