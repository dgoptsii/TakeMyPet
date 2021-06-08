package com.naukma.practice.myPet.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ComponentScan(basePackages = "com.naukma.practice.myPet.config")
public class SpringConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/css/*", "/owner/*/css/*", "/host/*/css/*").addResourceLocations("/css/");
        registry.addResourceHandler("/js/*", "/owner/*/js/*", "/host/*/js/*").addResourceLocations("/js/");
    }

}
