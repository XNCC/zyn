package com.example.zyn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zyn.mapper.index.NeedMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @Autowired
    NeedMapper needMapper;

    @GetMapping("/")
    public String totask(Model model) {
        Integer total = needMapper.selectCount(new QueryWrapper<>());
        Integer pageSize;
        if (total % 10 == 0) {
            pageSize = total / 10;
        } else {
            pageSize = total / 10 + 1;
        }
        model.addAttribute("total", total);
        model.addAttribute("pageSize", pageSize);
        return "index/task";
    }
    @GetMapping("/need")
    public String toNeed() {
        return "index/need";
    }

}
