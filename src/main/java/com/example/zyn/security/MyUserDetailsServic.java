package com.example.zyn.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsServic implements UserDetailsService {
    @Autowired
    private PasswordEncoder passwordEncoder;//一种单项加密手段，只能加密不能解密

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //这里假设一个从数据库查询到得密码123456，然后拿着这个密码跟前台传过来得密码进行对比。
        String password = passwordEncoder.encode("123456");
        return new User(username, password, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}

