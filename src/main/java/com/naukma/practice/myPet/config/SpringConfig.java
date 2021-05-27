package com.naukma.practice.myPet.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.sql.DataSource;

@Configuration
@ComponentScan(basePackages = "com.naukma.practice.myPet.config")
public class SpringConfig implements WebMvcConfigurer {

//    @Bean
//    public LocaleChangeInterceptor localChangeInterceptor() {
//        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
//        lci.setParamName("localeCode");
//        return lci;
//    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localChangeInterceptor());
//    }
//    @Bean
//    public LocaleResolver localeResolver() {
//        SessionLocaleResolver localeResolver = new SessionLocaleResolver();
//        localeResolver.setDefaultLocale(Locale.ENGLISH);
//        return localeResolver;
//    }
//
//    @Bean(name = "messageSource")
//    public ResourceBundleMessageSource getMessageSource(){
//        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//        messageSource.setBasename("lang");
//        messageSource.setDefaultEncoding("UTF-8");
//        messageSource.setUseCodeAsDefaultMessage(true);
//        return messageSource;
//    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        registry.addResourceHandler("/css/*","/owner/css/*","/host/css/*").addResourceLocations("/css/");
        registry.addResourceHandler("/js/*","/owner/js/*","/host/js/*").addResourceLocations("/js/");
    }

}
