package com.easytoolsoft.template.web.springmvc1.config.web;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import com.easytoolsoft.template.common.spring.converter.ResponseResult2HttpMessageConverter;
import com.easytoolsoft.template.common.spring.resolver.CurrentUserMethodArgumentResolver;
import com.easytoolsoft.template.common.spring.resolver.ResponseBodyWrapFactoryBean;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;

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

    @Override
    public void configureDefaultServletHandling(final DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    public ResponseResult2HttpMessageConverter messageConverter() {
        final ResponseResult2HttpMessageConverter converter = new ResponseResult2HttpMessageConverter();
        final ObjectMapper objectMapper = new ObjectMapper();
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
    public LocaleResolver localeResolver() {
        final CookieLocaleResolver localeResolver = new CookieLocaleResolver();
        //保存7天有效
        localeResolver.setCookieMaxAge(604800);
        localeResolver.setDefaultLocale(Locale.CHINA);
        localeResolver.setCookieName("locale");
        localeResolver.setCookiePath("/");
        return localeResolver;
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
