package com.example.zyn.controller;

import com.example.zyn.request.Need;
import com.example.zyn.service.index.TaskService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("task")
@CrossOrigin
public class TaskController {

    @Autowired
    TaskService taskService;

    @RequestMapping("/taskManager")
    @ResponseBody
    public Map<String, Object> taskManager(@RequestParam(value = "page", required = false, defaultValue = "1") int page, @RequestParam(value = "pageSize", required = false, defaultValue = "10") int pageSize) throws ParseException {
        Map<String, Object> result = new HashMap<String, Object>();
        List<Need> list = taskService.taskManager(page, pageSize);
        //将查询结果集封装到pageinfo对象中
        PageInfo<Need> pageInfo = new PageInfo<Need>(list);
        long total = pageInfo.getTotal();
        List<Need> blogList = pageInfo.getList();
        result.put("rows", blogList); //当前页面的数据
        result.put("total", pageInfo.getTotal());
        result.put("page", pageInfo.getPageNum());
        result.put("pagesize", pageSize);
        return result;
    }
}
