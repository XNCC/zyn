package com.example.zyn.response;

import lombok.Data;

@Data
public class Result<T> {
    private T data;
    private Integer code = 200;
    private String msg = "success";

    public Result(T data) {
        this.data = data;
    }

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

}
