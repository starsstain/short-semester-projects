package com.ccbupt.config;
 
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
	@Value("${imgurl}")
    private String imgurl;
	@Value("${imgpre}")
    private String imgpre;
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*") // 指定具体的来源http://localhost:5173
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Content-Type", "token", "jwt") // 允许的请求头
                .allowCredentials(false); // 如果不需要发送凭据，可以设置为 false
    }
    public void addResourceHandlers(ResourceHandlerRegistry registry) {  
     	registry.addResourceHandler("/"+imgpre+"**").addResourceLocations("file:"+ imgurl);  
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        
    }
}