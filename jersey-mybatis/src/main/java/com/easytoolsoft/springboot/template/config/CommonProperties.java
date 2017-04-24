package com.easytoolsoft.springboot.template.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * 公共配置类
 *
 * @author zhiwei.deng
 * @date 2017-04-11
 **/
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "easytoolsoft.springboot.template.common")
@PropertySource("classpath:conf/common.properties")
public class CommonProperties {
    private String item1;
    private String item2;
}
