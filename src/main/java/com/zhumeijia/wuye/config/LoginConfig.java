package com.zhumeijia.wuye.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 *
 *
 * @Package: com.*.*.config
 * @ClassName: LoginConfig
 * @Description:拦截器配置
 * @author: zk
 * @date: 2019年9月19日 下午2:18:35
 */
@Configuration
public class LoginConfig implements WebMvcConfigurer {

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

}