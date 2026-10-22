package com.ccbupt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebMVC配置类：解决静态资源（图片）访问映射问题
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    /**
     * 配置静态资源映射规则，核心解决图片访问路径问题
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // ========== 关键修改：映射房屋图片本地路径 ==========
        // 1. 本地图片实际路径（替换为你的绝对路径，用/或\避免转义）
        String houseImgLocalPath = "file:C:/Users/47455/Desktop/2026/jkshop/src/main/resources/static/img/upload/house/";
        // 2. 前端访问路径：/img/upload/house/xxx.jpg 会映射到上面的本地路径
        registry.addResourceHandler("/img/upload/house/**")
                .addResourceLocations(houseImgLocalPath)
                .setCachePeriod(0); // 开发阶段禁用缓存，避免图片修改后不生效
        
        // 添加额外映射：处理数据库中存储的旧路径格式（uploads/house/xxx.jpg）
        registry.addResourceHandler("/uploads/house/**")
                .addResourceLocations(houseImgLocalPath)
                .setCachePeriod(0);

        // ========== 保留默认静态资源映射（必须保留，否则CSS/JS等资源无法访问） ==========
        registry.addResourceHandler("/**")
                .addResourceLocations(
                        "classpath:/static/",
                        "classpath:/public/",
                        "classpath:/resources/",
                        "classpath:/META-INF/resources/"
                );
    }
}