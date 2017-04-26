package com.easytoolsoft.springboot.template.config.web;

import java.text.SimpleDateFormat;
import java.util.List;

import com.easytoolsoft.springboot.template.web.spring.converter.ResponseResult2HttpMessageConverter;
import com.easytoolsoft.springboot.template.web.spring.resolver.CurrentUserMethodArgumentResolver;
import com.easytoolsoft.springboot.template.web.spring.resolver.ResponseBodyWrapFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * spring mvc 配置类
 *
 * @author zhiwei.deng
 * @date 2017-04-11
 **/
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void extendMessageConverters(final List<HttpMessageConverter<?>> converters) {
        converters.add(messageConverter());
    }

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/rest/**");
    }

    @Override
    public void addArgumentResolvers(final List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver());
    }

    @Bean
    public ResponseResult2HttpMessageConverter messageConverter() {
        ResponseResult2HttpMessageConverter converter = new ResponseResult2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        converter.setObjectMapper(objectMapper);
        return converter;
    }

    @Bean
    public MultipartResolver multipartResolver() {
        final CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        //max file size 10M
        multipartResolver.setMaxUploadSize(10 * 1024 * 1024);
        return multipartResolver;
    }

    @Bean
    public HandlerMethodArgumentResolver currentUserMethodArgumentResolver() {
        return new CurrentUserMethodArgumentResolver();
    }

    @Bean
    public ResponseBodyWrapFactoryBean getResponseBodyWrap() {
        return new ResponseBodyWrapFactoryBean();
    }
}
