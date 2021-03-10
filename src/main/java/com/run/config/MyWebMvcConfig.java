package com.run.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry){


        registry.addMapping("/**")
                .allowedMethods("*")
                .maxAge(3800)
                .allowedOrigins("http://127.0.0.1:8848")
                .allowedHeaders("*")
                .allowCredentials(true)


        ;

    }

}
