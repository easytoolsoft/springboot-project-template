package com.easytoolsoft.template.data.mybatis.service.impl;

import javax.annotation.Resource;

import com.easytoolsoft.mybatis.service.AbstractCrudService;
import com.easytoolsoft.template.common.security.PasswordService;
import com.easytoolsoft.template.data.mybatis.repos.UserRepository;
import com.easytoolsoft.template.data.mybatis.domain.User;
import com.easytoolsoft.template.data.mybatis.domain.example.UserExample;
import com.easytoolsoft.template.data.mybatis.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author zhiwei.deng
 * @date 2017-03-25
 */
@Service
public class UserServiceImpl
    extends AbstractCrudService<UserRepository, User, UserExample, Integer>
    implements UserService {

    @Resource
    private PasswordService passwordService;

    @Override
    protected UserExample getPageExample(final String fieldName, final String keyword) {
        final UserExample example = new UserExample();
        example.createCriteria().andFieldLike(fieldName, keyword);
        return example;
    }

    @Override
    public User getUserByAccount(final String account) {
        final UserExample example = new UserExample();
        example.or().andAccountEqualTo(account);
        return this.dao.selectOneByExample(example);
    }

    @Override
    public void encryptPassword(final User user) {
        user.setSalt(this.passwordService.genreateSalt());
        user.setPassword(this.passwordService.encryptPassword(user.getPassword(), user.getCredentialsSalt()));
    }
}