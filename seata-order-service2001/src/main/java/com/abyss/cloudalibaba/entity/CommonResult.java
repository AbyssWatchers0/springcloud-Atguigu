package com.abyss.cloudalibaba.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Abyss Watchers
 * @create 2023-01-06 17:41
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private int code;
    private String msg;
    private T data;

    public CommonResult(String msg) {
        this(200, msg, null);
    }

    public CommonResult(String msg, T data) {
        this(200, msg, data);
    }

    public CommonResult(int code, String msg) {
        this(code, msg, null);
    }
}
