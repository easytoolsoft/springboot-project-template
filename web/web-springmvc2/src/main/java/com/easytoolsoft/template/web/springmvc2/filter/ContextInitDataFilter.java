package com.easytoolsoft.template.web.springmvc2.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.easytoolsoft.springboot.template.common.Constants;

/**
 * ServletContext 初始化数据 Filter
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public class ContextInitDataFilter implements Filter {
    private String version = Constants.VERSION;
    private String env = Constants.ENV;

    @Override
    public void init(final FilterConfig filterConfig) throws ServletException {
        this.version = filterConfig.getInitParameter(Constants.VERSION_ITEM);
        this.env = filterConfig.getInitParameter(Constants.ENV_ITEM);
    }

    @Override
    public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
        throws IOException, ServletException {
        final HttpServletRequest req = (HttpServletRequest)request;

        request.setAttribute(Constants.APP_NAME_ITEM, Constants.APP_NAME);
        if (request.getAttribute(Constants.CONTEXT_PATH) == null) {
            request.setAttribute(Constants.CONTEXT_PATH, req.getContextPath());
        }
        if (request.getAttribute(Constants.VERSION_ITEM) == null) {
            request.setAttribute(Constants.VERSION_ITEM, this.version);
        }
        if (request.getAttribute(Constants.ENV_ITEM) == null) {
            request.setAttribute(Constants.ENV_ITEM, this.env);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
