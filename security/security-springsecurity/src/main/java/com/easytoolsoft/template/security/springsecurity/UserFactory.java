package com.easytoolsoft.template.security.springsecurity;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.easytoolsoft.template.data.mybatis.domain.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

/**
 * @author zhiwei.deng
 * @date 2017-05-12
 **/
public final class UserFactory {

    private UserFactory() {
    }

    public static MyUserDetails create(User user, Set<String> authorities) {
        return new MyUserDetails(
            Long.valueOf(user.getId()),
            user.getAccount(),
            user.getName(),
            "",
            user.getEmail(),
            user.getPassword(),
            mapToGrantedAuthorities(authorities),
            user.isEnabled(),
            user.getGmtModified()
        );
    }

    private static List<GrantedAuthority> mapToGrantedAuthorities(Set<String> authorities) {
        return authorities.stream()
            .map(authority -> new SimpleGrantedAuthority(authority))
            .collect(Collectors.toList());
    }
}
