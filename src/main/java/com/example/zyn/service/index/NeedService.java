package com.example.zyn.service.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zyn.mapper.index.NeedMapper;
import com.example.zyn.request.Need;
import com.example.zyn.response.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NeedService {

    @Autowired
    NeedMapper userMapper;


    public Need get(Integer id){
        QueryWrapper<Need> queryWrapper=new QueryWrapper<Need>();
        queryWrapper.eq("id",id);
        Need need = userMapper.selectOne(queryWrapper);
        return need;
    }
}
