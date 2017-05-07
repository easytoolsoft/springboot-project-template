package com.easytoolsoft.template.data.mybatis.repos;

import java.util.List;

import com.easytoolsoft.mybatis.data.CrudRepository;
import com.easytoolsoft.template.data.mybatis.domain.Permission;
import com.easytoolsoft.template.data.mybatis.domain.example.PermissionExample;
import org.springframework.stereotype.Repository;

/**
 * 系统权限(ezrpt_member_permission表)数据访问类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Repository
public interface PermissionRepository extends CrudRepository<Permission, PermissionExample, Integer> {
    List<Permission> selectAllWithModulePath();
}
