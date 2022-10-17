package com.example.demo.common;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 图片绝对地址与虚拟地址映射
 */

@Configuration
public class URLConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //文件磁盘图片url 映射
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        registry.addResourceHandler("/resource/**").addResourceLocations("file:/C:/Users/Jarvis2K/Desktop/小学期2/Workspace/management/src/resource/");
        registry.addResourceHandler("/video/view/**").addResourceLocations("file:/E:/video/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }
}
