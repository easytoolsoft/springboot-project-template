package com.easytoolsoft.template.data.mybatis.service;

import java.util.List;
import java.util.Map;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.mybatis.service.CrudService;
import com.easytoolsoft.template.data.mybatis.domain.Role;
import com.easytoolsoft.template.data.mybatis.domain.User;
import com.easytoolsoft.template.data.mybatis.domain.example.RoleExample;

/**
 * 系统角色服务类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public interface RoleService extends CrudService<Role, RoleExample, Integer> {
    /**
     * @param roleIds
     * @return
     */
    boolean isSuperAdminRole(String roleIds);

    /**
     * @param roleIds
     * @return
     */
    String getNames(String roleIds);

    /**
     * @param roleIds
     * @return
     */
    String getModuleIds(String roleIds);

    /**
     * @param roleIds
     * @return
     */
    String getPermissionIds(String roleIds);

    /**
     * @param page
     * @param currentUser
     * @param fieldName
     * @param keyword
     * @return
     */
    List<Role> getByPage(PageInfo page, User currentUser, String fieldName, String keyword);

    /**
     * @param createUser
     * @return
     */
    String getRoleIdsBy(String createUser);

    /**
     * @param currentUser
     * @return
     */
    List<Role> getRolesList(User currentUser);

    /**
     * @param roleId
     * @return
     */
    Map<String, String[]> getRoleModulesAndPermissions(Integer roleId);
}