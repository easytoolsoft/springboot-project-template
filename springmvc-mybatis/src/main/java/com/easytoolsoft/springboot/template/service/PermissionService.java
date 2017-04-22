package com.easytoolsoft.springboot.template.service;

import java.util.List;
import java.util.Map;

import com.easytoolsoft.mybatis.pager.PageInfo;
import com.easytoolsoft.mybatis.service.CrudService;
import com.easytoolsoft.springboot.template.domain.Permission;
import com.easytoolsoft.springboot.template.domain.example.PermissionExample;

/**
 * 系统权限服务类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public interface PermissionService extends CrudService<Permission, PermissionExample, Integer> {
    /**
     *
     */
    void reloadCache();

    /**
     * @param pageInfo
     * @param moduleId
     * @return
     */
    List<Permission> getByPage(PageInfo pageInfo, Integer moduleId);

    /**
     * @param moduleId
     * @return
     */
    List<Permission> getByModuleId(Integer moduleId);

    /**
     * @return
     */
    Map<String, String> getIdCodeMap();

    /**
     * @param codes
     * @return
     */
    String getPermissionIds(String[] codes);

    /**
     * @param permissionIds
     * @return
     */
    String getModuleIds(String permissionIds);
}