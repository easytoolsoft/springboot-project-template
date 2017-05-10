package com.easytoolsoft.template.web.springmvc1.config.web;

import com.easytoolsoft.template.web.springmvc1.config.properties.EnvProperties;
import org.apache.catalina.servlets.DefaultServlet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorProperties;
import org.springframework.boot.autoconfigure.web.ErrorProperties.IncludeStacktrace;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.ErrorPage;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;

/**
 * java web servlet 配置类
 *
 * @author zhiwei.deng
 * @date 2017-04-11
 **/
@Configuration
@EnableConfigurationProperties(EnvProperties.class)
public class ServletConfig {
    @Autowired
    private EnvProperties envProperties;

    /**
     * 让static下的静态资源走DefaultServlet, 不走SpringMVC DispatchServlet
     *
     * @return ServletRegistrationBean
     */
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        return new ServletRegistrationBean(new DefaultServlet(), "/static/*");
    }

    /**
     * 在系统启动时加一些初始化数据到request上下文中
     *
     * @return FilterRegistrationBean
     */
    @Bean
    public FilterRegistrationBean contextInitDataFilterRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ContextInitDataFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter(AppEnvConstants, this.envProperties.getName());
        registrationBean.addInitParameter(AppEnvConstants.ENV_ITEM, this.envProperties.getName());
        registrationBean.addInitParameter(AppEnvConstants.VERSION_ITEM, this.envProperties.getVersion());
        registrationBean.setName("contextInitDataFilter");
        return registrationBean;
    }

    @Bean
    public EmbeddedServletContainerCustomizer containerCustomizer() {
        return container -> {
            container.addErrorPages(new ErrorPage(HttpStatus.NOT_FOUND, "/error/404"));
            container.addErrorPages(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, "/error"));
        };
    }

    @Bean
    public ErrorProperties errorProperties() {
        final ErrorProperties properties = new ErrorProperties();
        properties.setIncludeStacktrace(IncludeStacktrace.ALWAYS);
        return properties;
    }
}
