package com.easytoolsoft.template.web.springmvc2.config.web;

import com.easytoolsoft.template.web.springmvc2.config.EnvProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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
}
