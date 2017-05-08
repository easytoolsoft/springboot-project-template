package com.easytoolsoft.template.web.springmvc2.config.web;

import java.nio.charset.Charset;

import com.easytoolsoft.template.common.spring.i18n.MyResourceBundleMessageSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

/**
 * @author zhiwei.deng
 * @date 2017-05-08
 **/
@Configuration
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.messages")
public class MessageSourceConfig {
    private String basename = "messages";
    private Charset encoding = Charset.forName("UTF-8");
    private int cacheSeconds = -1;
    private boolean fallbackToSystemLocale = true;
    private boolean alwaysUseMessageFormat = false;

    @Bean
    public MessageSource messageSource() {
        MyResourceBundleMessageSource messageSource = new MyResourceBundleMessageSource();
        if (StringUtils.hasText(this.basename)) {
            messageSource.setBasenames(StringUtils.commaDelimitedListToStringArray(
                StringUtils.trimAllWhitespace(this.basename)));
        }
        if (this.encoding != null) {
            messageSource.setDefaultEncoding(this.encoding.name());
        }
        messageSource.setFallbackToSystemLocale(this.fallbackToSystemLocale);
        messageSource.setCacheSeconds(this.cacheSeconds);
        messageSource.setAlwaysUseMessageFormat(this.alwaysUseMessageFormat);
        return messageSource;
    }

    public String getBasename() {
        return this.basename;
    }

    public void setBasename(String basename) {
        this.basename = basename;
    }

    public Charset getEncoding() {
        return this.encoding;
    }

    public void setEncoding(Charset encoding) {
        this.encoding = encoding;
    }

    public int getCacheSeconds() {
        return this.cacheSeconds;
    }

    public void setCacheSeconds(int cacheSeconds) {
        this.cacheSeconds = cacheSeconds;
    }

    public boolean isFallbackToSystemLocale() {
        return this.fallbackToSystemLocale;
    }

    public void setFallbackToSystemLocale(boolean fallbackToSystemLocale) {
        this.fallbackToSystemLocale = fallbackToSystemLocale;
    }

    public boolean isAlwaysUseMessageFormat() {
        return this.alwaysUseMessageFormat;
    }

    public void setAlwaysUseMessageFormat(boolean alwaysUseMessageFormat) {
        this.alwaysUseMessageFormat = alwaysUseMessageFormat;
    }
}
