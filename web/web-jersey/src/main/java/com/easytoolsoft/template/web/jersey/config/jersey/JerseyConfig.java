package com.easytoolsoft.template.web.jersey.config.jersey;

import javax.annotation.PostConstruct;

import com.easytoolsoft.template.web.jersey.rest.v1.EventResource;
import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.wadl.internal.WadlResource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zhiwei.deng
 * @date 2017-04-11
 **/
@Component
public class JerseyConfig extends ResourceConfig {
    @Value("${server.host}")
    private String host;
    @Value("${server.port}")
    private int port;
    @Value("${spring.jersey.application-path}")
    private String apiPath;

    public JerseyConfig() {
        this.registerEndpoints();
    }

    @PostConstruct
    public void init() {
        this.configureSwagger();
    }

    private void registerEndpoints() {
        this.register(EventResource.class);
        this.register(com.easytoolsoft.template.web.jersey.rest.v2.EventResource.class);
        this.register(WadlResource.class);
    }

    private void configureSwagger() {
        this.register(ApiListingResource.class);
        this.register(SwaggerSerializers.class);

        BeanConfig config = new BeanConfig();
        config.setConfigId("SpringBoot-Project-Template-Web-Jersey");
        config.setTitle("SpringBoot-Project-Template-Web-Jersey");
        config.setVersion("v1");
        config.setContact("easytoolsoft");
        config.setHost(host + ":" + port);
        config.setSchemes(new String[] {"http", "https"});
        config.setBasePath(this.apiPath);
        config.setResourcePackage("com.easytoolsoft.template.web.jersey.rest");
        config.setPrettyPrint(true);
        config.setScan(true);
    }
}