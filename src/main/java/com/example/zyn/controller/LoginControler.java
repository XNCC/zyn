package com.example.zyn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zyn.mapper.index.UserMapper;
import com.example.zyn.request.User;
import com.example.zyn.response.Result;
import com.example.zyn.response.UserInfoResp;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("login")
@CrossOrigin
public class LoginControler {

    @Autowired
    UserMapper userMapper;

    @PostMapping("/loginByEmail")
    public Result loginByEmail(@RequestBody User user, HttpServletRequest request) {
        UserInfoResp userInfoResp = new UserInfoResp();
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username", user.getUsername());
        User user1 = userMapper.selectOne(userQueryWrapper);
        // 系统登录认证
        userInfoResp.setUser(user1);
        return new Result<>(200,"登录成功",userInfoResp);
    }

    @PostMapping("/registByEmail")
    public Result registByEmail(@RequestBody User user, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.eq("username",user.getUsername());
        User oldUser = userMapper.selectOne(queryWrapper);
        if(ObjectUtils.isNotEmpty(oldUser)){
            return new Result<>(500,"该用户名已存在",null);
        }
        String password = user.getPassword();
        user.setPassword(new BCryptPasswordEncoder().encode(password));
        return new Result<>(userMapper.insert(user));
    }
}
