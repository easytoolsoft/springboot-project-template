package com.easytoolsoft.template.data.mybatis.repos;

import com.easytoolsoft.mybatis.data.CrudRepository;
import com.easytoolsoft.template.data.mybatis.domain.Module;
import com.easytoolsoft.template.data.mybatis.domain.example.ModuleExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * 系统模块(ezrpt_member_module表)数据访问类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Repository
public interface ModuleRepository extends CrudRepository<Module, ModuleExample, Integer> {
    int updatePath(@Param("oldPath") String oldPath, @Param("newPath") String newPath);
}
