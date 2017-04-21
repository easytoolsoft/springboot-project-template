package com.easytoolsoft.springboot.template.config.springmvcmybatis;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 自定义配置类
 *
 * @author zhiwei.deng
 * @date 2017-04-19
 **/
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "easytoolsoft.springboot.template")
@PropertySource("classpath:conf/springmvcmybatis/config.properties")
public class ConfigProperties {
    private String item1;
    private String item2;
}
