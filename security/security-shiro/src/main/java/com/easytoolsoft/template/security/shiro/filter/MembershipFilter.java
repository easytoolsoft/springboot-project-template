package com.easytoolsoft.template.security.shiro.filter;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.easytoolsoft.commons.support.consts.UserAuthConsts;
import com.easytoolsoft.commons.support.security.MembershipFacade;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.web.filter.PathMatchingFilter;

/**
 * 自定义的Shiro Filter,用于处理用户是否登录
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public class MembershipFilter extends PathMatchingFilter {
    @Resource
    private MembershipFacade membershipFacade;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue)
        throws Exception {
        String account = (String)SecurityUtils.getSubject().getPrincipal();
        request.setAttribute(UserAuthConsts.CURRENT_USER, membershipFacade.getUser(account));
        return true;
    }
}
