package com.easytoolsoft.template.web.springmvc1.config.properties;

import javax.validation.Valid;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

/**
 * 自定义配置类
 *
 * @author zhiwei.deng
 * @date 2017-04-19
 **/
@Getter
@Setter
@Validated
@Configuration
@ConfigurationProperties(prefix = "web.springmvc1")
@PropertySource("classpath:conf/properties/config.properties")
public class ConfigProperties {
    @Valid
    private SpringSecurity springSecurity = new SpringSecurity();

    public static class SpringSecurity {
        private String filters;

        public String getFilters() {
            return filters;
        }

        public void setFilters(String filters) {
            this.filters = filters;
        }
    }
}
