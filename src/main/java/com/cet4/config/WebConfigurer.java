package com.cet4.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfigurer implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/js/**","/css/**","/images/**","/login","/logout","/index");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //请求返回前端界面
        registry.addViewController("/index").setViewName("index");
        registry.addViewController("/main").setViewName("main");
    }
}
