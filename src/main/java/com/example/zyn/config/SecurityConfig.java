package com.example.zyn.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().disable();
        // 禁用 csrf, 由于使用的是JWT，我们这里不需要csrf
        http.cors().and().csrf().disable();
        http.authorizeRequests()
                .antMatchers("/need/update").permitAll()
                .antMatchers("/need/updateDraft").permitAll()
                .antMatchers("/need/upload").permitAll()
                .antMatchers("/user/guestNeed/**").permitAll()
                .antMatchers("/static/**").permitAll()
                .anyRequest()
                .authenticated()//以上三局表示所有请求都要验证
                .and()
                .formLogin().defaultSuccessUrl("/user/need") ;

    }

}
