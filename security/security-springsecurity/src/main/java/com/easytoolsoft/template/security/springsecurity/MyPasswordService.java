package com.easytoolsoft.template.security.springsecurity;

import javax.annotation.Resource;

import com.easytoolsoft.commons.support.security.PasswordService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @author zhiwei.deng
 * @date 2017-05-14
 **/
@Service("SpringSecurityPasswordService")
public class MyPasswordService implements PasswordService {
    @Resource
    private PasswordEncoder passwordEncoder;

    @Override
    public String genreateSalt() {
        return null;
    }

    @Override
    public String encode(CharSequence rawPassword, String credentialsSalt) {
        return null;
    }

    @Override
    public String encode(CharSequence rawPassword) {
        return this.passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return this.passwordEncoder.matches(rawPassword, encodedPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword, String credentialsSalt) {
        return false;
    }
}
