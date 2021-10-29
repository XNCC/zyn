package com.example.zyn.service.index;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zyn.mapper.index.NeedMapper;
import com.example.zyn.request.Need;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {
    @Autowired
    NeedMapper needMapper;

    public List<Need> taskManager(int page, int pageSize) {
        PageHelper.startPage(page, pageSize);
        return needMapper.selectList(new QueryWrapper<Need>());
    }

}
