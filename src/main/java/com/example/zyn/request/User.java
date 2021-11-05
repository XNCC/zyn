package com.example.zyn.request;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@TableName("user")
public class User implements Serializable {

    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String username;
    private String password;
    private Date createTime;
    private Date updateTime;
    private String token;
    private Date expireTime;
}
