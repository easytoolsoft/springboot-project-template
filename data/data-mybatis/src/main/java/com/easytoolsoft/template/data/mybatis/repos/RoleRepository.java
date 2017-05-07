package com.easytoolsoft.template.data.mybatis.repos;

import com.easytoolsoft.mybatis.data.CrudRepository;
import com.easytoolsoft.template.data.mybatis.domain.Role;
import com.easytoolsoft.template.data.mybatis.domain.example.RoleExample;
import org.springframework.stereotype.Repository;

/**
 * 系统角色(ezrpt_member_role表)数据访问类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Repository
public interface RoleRepository extends CrudRepository<Role, RoleExample, Integer> {
}
