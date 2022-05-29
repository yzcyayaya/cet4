package com.cet4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * springmvc 拦截器
 */
@Configuration
public class WebConfigurer implements WebMvcConfigurer {

//    @Override
//    public void addInterceptors(InterceptorRegistry registry){
//        registry.addInterceptor(new LoginInterceptor())
//                .addPathPatterns("/**")
//                .excludePathPatterns("/js/**","/css/**","/images/**","/user/login","/user/logout","/index","/main");
//    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //请求返回前端界面
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/main").setViewName("main");
        registry.addViewController("/unauth").setViewName("unauth");
        registry.addViewController("/administrator").setViewName("administrator");
    }
}
