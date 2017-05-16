package com.easytoolsoft.template.security.springsecurity;

import java.util.Set;

import com.easytoolsoft.commons.support.security.MembershipFacade;
import com.easytoolsoft.template.data.mybatis.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author zhiwei.deng
 * @date 2017-05-12
 **/
@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    private MembershipFacade<User> membershipFacade;

    @Override
    public UserDetails loadUserByUsername(String username)
        throws UsernameNotFoundException {
        User user = this.membershipFacade.getUser(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));
        } else {
            Set<String> authorities = membershipFacade.getPermissionSet(user.getRoles());
            return UserFactory.create(user, authorities);
        }
    }
}
