package com.example.zyn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zyn.mapper.index.NeedMapper;
import com.example.zyn.request.Need;
import com.example.zyn.service.index.NeedService;
import com.example.zyn.service.index.TaskService;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Controller
@RequestMapping("user")
@CrossOrigin
public class IndexController {
    @Autowired
    TaskService taskService;

    @Autowired
    NeedService needService;

    @Autowired
    NeedMapper needMapper;

    @GetMapping("/index")
    public String toIndex() {
        return "index/index";
    }

    @GetMapping("/need")
    public String toNeed() {
        return "index/need";
    }

    @GetMapping("/need/{id}")
    public String toadminNeed(Model model, @PathVariable("id") Integer id) {
        Need need = needService.get(id);
        if (ObjectUtils.isEmpty(need)) {
            return "error/404";
        }
        List<String> urlList = new ArrayList<>();
        if (StringUtils.isNotEmpty(need.getUrl())) {
            String[] split = need.getUrl().split(",");
            urlList = Arrays.asList(split);
        }
        model.addAttribute("need", need);
        model.addAttribute("urlList", urlList);
        return "index/guestNeed";
    }

    @GetMapping("/adminNeed")
    public String toAdminNeed() {
        return "index/adminNeed";
    }

    @GetMapping("/task")
    public String totask(Model model) {
       Integer total= needMapper.selectCount(new QueryWrapper<>());
       Integer pageSize;
       if(total%10==0){
           pageSize=total/10;
       }else{
            pageSize=total/10+1;
        }
       model.addAttribute("total",total);
       model.addAttribute("pageSize",pageSize);
        return "index/task";
    }

    @GetMapping("/contrl")
    public String tocontrol(Model model) {
        model.addAttribute("formType", "need");
        return "index/control";
    }

    @GetMapping("/login")
    public String tologin() {
        return "index/login";
    }

    @GetMapping("/regist")
    public String toregist() {
        return "index/regist";
    }

    @GetMapping("/guestNeed/{id}/{value}")
    public String toGuestNeed(Model model, @PathVariable("id") Integer id, @PathVariable("value") Integer value) {
        Integer realId = id >> 16;
        if (realId != value) {
            return "error/404";
        }
        Need need = needService.get(realId);
        if (ObjectUtils.isEmpty(need)) {
            return "error/404";
        }
        List<String> urlList = new ArrayList<>();
        if (StringUtils.isNotEmpty(need.getUrl())) {
            String[] split = need.getUrl().split(",");
            urlList = Arrays.asList(split);
        }

        model.addAttribute("need", need);
        model.addAttribute("urlList", urlList);
        return "index/guestNeed";
    }
}
