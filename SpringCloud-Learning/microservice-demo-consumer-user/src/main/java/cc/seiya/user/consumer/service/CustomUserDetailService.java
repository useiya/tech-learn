package cc.seiya.user.consumer.service;

import lombok.Data;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author libo
 * @date 2018/1/22  22:45
 */
//@Component
public class CustomUserDetailService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        if ("user".equals(s)) {
            return new SecurityUser("user", "user", "user-role");
        }
        if ("admin".equals(s)) {
            return new SecurityUser("admin", "admin", "admin-role");
        }
        return null;
    }


    @Data
    public static class SecurityUser implements UserDetails {

        private Long id;
        private String username;
        private String password;
        private String role;

        public SecurityUser(String username, String password, String role) {
            super();
            this.username = username;
            this.password = password;
            this.role = role;
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            List<GrantedAuthority> authorities = new ArrayList<>(3);
            SimpleGrantedAuthority simpleGrantedAuthority = new SimpleGrantedAuthority(this.role);
            authorities.add(simpleGrantedAuthority);
            return authorities;
        }

        @Override
        public boolean isAccountNonExpired() {
            return true;
        }

        @Override
        public boolean isAccountNonLocked() {
            return true;
        }

        @Override
        public boolean isCredentialsNonExpired() {
            return true;
        }

        @Override
        public boolean isEnabled() {
            return true;
        }
    }
}
