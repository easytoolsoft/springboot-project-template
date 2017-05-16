package com.easytoolsoft.template.web.springmvc1.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;

/**
 * @author zhiwei.deng
 * @date 2017-05-11
 **/
@EnableRedisHttpSession
public class HttpSessionConfig {
    @Bean
    public CookieSerializer cookieSerializer() {
        DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
        cookieSerializer.setCookieName("SPRINGMVC1_JSESSIONID");
        cookieSerializer.setCookiePath("/");
        return cookieSerializer;
    }
}
