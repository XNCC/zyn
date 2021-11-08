package com.example.zyn.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.zyn.mapper.index.NeedMapper;
import com.example.zyn.request.BaseReq;
import com.example.zyn.request.Need;
import com.example.zyn.response.Result;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RequestMapping("need")
@CrossOrigin
@RestController
public class NeedController {

    @Value("${zyn.localPath}")
    private String filePath;

    @Autowired
    NeedMapper needMapper;

    /**
     * 上传文件
     */
    @PostMapping("/upload")
    public Result upload(@RequestParam(value = "file") MultipartFile[] files, HttpServletRequest request) {
        String url = "";
        Map map = new HashMap();
        for (MultipartFile file : files) {
            if (file.isEmpty()) {
                throw new RuntimeException("文件不能为空");
            }
            if (url.length() != 0) {
                url = url + ",";
            }
            String fileName = file.getOriginalFilename();  // 文件名
            String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
            File dest = new File(filePath);
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            fileName = uuid + suffixName; // 新文件名
            File fils = new File(dest.getAbsolutePath() + File.separator + fileName);
            System.out.println(dest.getAbsolutePath() + File.separator + fileName);
            try {
                file.transferTo(fils);
                url += fileName;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        map.put("url", url);
        return new Result(200, "上传成功", map);
    }

    @PostMapping("/save")
    public Result save(@RequestBody Need need) {
        need.setCreateTime(new Date());
        need.setStatus(2);
        need.setRock(2);
        needMapper.insert(need);
        return new Result(200, "保存成功", null);
    }

    @PostMapping("/draft")
    public Result draft(@RequestBody Need need) {
        need.setCreateTime(new Date());
        need.setStatus(1);
        need.setRock(2);
        needMapper.insert(need);
        return new Result(200, "保存草稿成功", null);
    }

    @PostMapping("/update")
    public Result update(@RequestBody Need need) {
        Need need1 = needMapper.selectById(need.getId());
        if (ObjectUtils.isNotEmpty(need1)) {
            if (need1.getRock() == 1) {
                return new Result(200, "该文档已被锁定，不接受更改", null);
            }
        }
        need.setCreateTime(new Date());
//        need.setId();
        need.setStatus(2);
        need.setRock(2);
        needMapper.updateById(need);
        return new Result(200, "更新成功", null);
    }

    @PostMapping("/updateDraft")
    public Result updateDraft(@RequestBody Need need) {
        Need need1 = needMapper.selectById(need.getId());
        if (ObjectUtils.isNotEmpty(need1)) {
            if (need1.getRock() == 1) {
                return new Result(200, "该文档已被锁定，不接受更改", null);
            }
        }
        need.setCreateTime(new Date());
        need.setStatus(1);
        need.setRock(2);
        needMapper.updateById(need);
        return new Result(200, "更新草稿成功", null);
    }

    @PostMapping("/release")
    public Result release(@RequestBody BaseReq baseReq) {
        Need need=new Need();
        need.setId(baseReq.getId());
        need.setRock(1);
        need.setStatus(2);
        needMapper.updateById(need);
        return new Result(200, "发布成功", null);
    }
}
