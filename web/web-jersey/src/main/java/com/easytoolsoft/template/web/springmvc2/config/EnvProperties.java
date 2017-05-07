package com.easytoolsoft.template.web.springmvc2.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 应用环境配置类
 *
 * @author zhiwei.deng
 * @date 2017-04-11
 **/
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "easytoolsoft.springboot.template.env")
@PropertySource("classpath:conf/env.properties")
public class EnvProperties {
    private String appName;
    private String name;
    private String version;
}
