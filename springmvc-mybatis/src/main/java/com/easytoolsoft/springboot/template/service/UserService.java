package com.easytoolsoft.springboot.template.service;

import com.easytoolsoft.mybatis.service.CrudService;
import com.easytoolsoft.springboot.template.domain.User;
import com.easytoolsoft.springboot.template.domain.example.UserExample;

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