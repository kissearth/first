package com.ylkj.xxb.config;

import com.ylkj.xxb.support.CorsFilter;
import com.ylkj.xxb.support.Date2StringConverter;
import com.ylkj.xxb.support.ErrorMvcAttributes;
import com.ylkj.xxb.support.String2DateConverter;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public FilterRegistrationBean<CorsFilter> customCorsFilterRegistration() {
        FilterRegistrationBean<CorsFilter> registration = new FilterRegistrationBean<>();
        registration.setFilter(new CorsFilter());
        registration.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registration;
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Date2StringConverter());
        registry.addConverter(new String2DateConverter());
    }

    @Bean
    public ErrorAttributes errorAttributes() {
        return new ErrorMvcAttributes();
    }

    @Bean
    public ErrorProperties errorProperties() {
        return new ErrorProperties();
    }

}
