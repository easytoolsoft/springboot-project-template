package com.easytoolsoft.template.data.mybatis.service;

import com.easytoolsoft.mybatis.service.CrudService;
import com.easytoolsoft.template.data.mybatis.domain.User;
import com.easytoolsoft.template.data.mybatis.domain.example.UserExample;

/**
 * 系统用户服务类
 *
 * @author zhiwei.deng
 * @date 2017-03-25
 */
public interface UserService extends CrudService<User, UserExample, Integer> {
    /**
     * @param account
     * @return
     */
    User getUserByAccount(String account);

    /**
     * @param user
     */
    void encryptPassword(User user);
}