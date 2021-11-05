package com.example.zyn.response;

import com.example.zyn.request.User;
import jdk.nashorn.internal.parser.Token;
import lombok.Data;

import java.io.Serializable;

@Data
public class UserInfoResp implements Serializable {

    private User user;
    private String token;

}
