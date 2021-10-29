package com.example.zyn.controller;

import com.example.zyn.service.index.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
public class IndexController {
    @Autowired
    TaskService taskService;

    @GetMapping("/index")
    public String toIndex() {
        return "index/index";
    }
    @GetMapping("/need")
    public String toNeed() {
        return "index/need";
    }

    @GetMapping("/task")
    public String totask( ) {
        return "index/task";
    }

    @GetMapping("/contrl")
    public String tocontrol(Model model) {
        model.addAttribute("formType","need");
        return "index/control";
    }
}
