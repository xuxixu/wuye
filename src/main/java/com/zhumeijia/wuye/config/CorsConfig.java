package com.zhumeijia.wuye.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

@Configuration
public class CorsConfig extends WebMvcConfigurationSupport {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/*").excludePathPatterns(
                "/login",
                "/user/login",
                "/static/assets/css/*.*",
                "/static/assets/images/*.*",
                "/static/assets/js/*.*",
                "/static/assets/module/*.*",
                "/static/assets/module/**/*.*",

                "/static/assets/libs/echarts/*.*",
                "/static/assets/libs/jquery/*.*",

                "/static/user/images/*.*",

                "/static/assets/libs/layui/*.*",
                "/static/assets/libs/layui/css/*.*",
                "/static/assets/libs/layui/css/modules/laydate/default/*.*",
                "/static/assets/libs/layui/css/modules/layer/default/*.*",
                "/static/assets/libs/layui/font/*.*",
                "/static/assets/libs/layui/images/face/*.*",
                "/static/assets/libs/layui/lay/modules/*.*"


        );

    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("/login");
    }

    @Override
    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("file:" +           System.getProperty("user.dir")+"\\src\\main\\resources\\static\\");
        super.addResourceHandlers(registry);
    }

}
