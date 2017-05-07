package com.easytoolsoft.template.data.mybatis.repos;

import com.easytoolsoft.mybatis.data.CrudRepository;
import com.easytoolsoft.template.data.mybatis.domain.User;
import com.easytoolsoft.template.data.mybatis.domain.example.UserExample;
import org.springframework.stereotype.Repository;

/**
 * 系统用户(ezrpt_member_user表)数据访问类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Repository
public interface UserRepository extends CrudRepository<User, UserExample, Integer> {
}
