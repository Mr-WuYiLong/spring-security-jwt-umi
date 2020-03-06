package com.wyl.springbootjwt.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description CorsConfig 跨域配置
 * @Author YiLong Wu
 * @Date 2020/2/28 17:02
 * @Version 1.0.0
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }

}
