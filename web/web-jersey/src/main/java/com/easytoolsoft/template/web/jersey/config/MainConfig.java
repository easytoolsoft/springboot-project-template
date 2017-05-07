package com.easytoolsoft.template.web.jersey.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 根配置类，自动扫描basePackages下的所有bean
 *
 * @author zhiwei.deng
 * @date 2017-04-10
 **/
@Configuration
@ComponentScan(basePackages = {"com.easytoolsoft.template.web.jersey"})
public class MainConfig {
}
