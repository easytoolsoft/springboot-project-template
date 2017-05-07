package com.easytoolsoft.template.data.mybatis.service;

import java.util.List;
import java.util.function.Predicate;

import com.easytoolsoft.template.common.auth.MembershipFacade;
import com.easytoolsoft.template.common.tree.EasyUITreeNode;
import com.easytoolsoft.template.data.mybatis.domain.Module;

/**
 * 用户权限服务外观类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public interface MyMembershipFacade extends MembershipFacade {
    void loadCache();

    List<EasyUITreeNode<Module>> getModuleTree(final List<Module> modules, final Predicate<Module> predicate);

    List<Module> getModules(final String roleIds);
}
