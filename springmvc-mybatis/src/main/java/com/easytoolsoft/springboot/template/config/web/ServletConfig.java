package com.easytoolsoft.springboot.template.config.web;

import com.easytoolsoft.springboot.template.common.Constants;
import com.easytoolsoft.springboot.template.config.properties.EnvProperties;
import com.easytoolsoft.springboot.template.web.filter.ContextInitDataFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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

    @Bean
    public FilterRegistrationBean contextInitDataFilterRegistrationBean() {
        final FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new ContextInitDataFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.addInitParameter(Constants.ENV_ITEM, this.envProperties.getName());
        registrationBean.addInitParameter(Constants.VERSION_ITEM, this.envProperties.getVersion());
        registrationBean.setName("contextInitDataFilter");
        return registrationBean;
    }

    //@Bean
    //public ServletRegistrationBean servletRegistrationBean() {
    //    return new ServletRegistrationBean(new DefaultServlet(), "/static/*");
    //}
}
