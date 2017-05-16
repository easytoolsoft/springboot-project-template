package com.easytoolsoft.template.data.mybatis.service;

import java.util.List;
import java.util.function.Predicate;

import com.easytoolsoft.commons.lang.tree.EasyUITreeNode;
import com.easytoolsoft.commons.support.security.MembershipFacade;
import com.easytoolsoft.template.data.mybatis.domain.Module;
import com.easytoolsoft.template.data.mybatis.domain.User;

/**
 * 用户权限服务外观类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public interface MembershipFacadeService extends MembershipFacade<User> {
    void loadCache();

    List<EasyUITreeNode<Module>> getModuleTree(final List<Module> modules, final Predicate<Module> predicate);

    List<Module> getModules(final String roleIds);
}
