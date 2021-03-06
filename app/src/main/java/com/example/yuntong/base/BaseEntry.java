package com.example.yuntong.base;


import com.example.yuntong.utils.MainUtil;

import java.io.Serializable;

/**
 * @author 杨晓峰
 * @create 2019/5/14
 * @Describe base 返回基础泛型
 */

public class BaseEntry<T> implements Serializable{

    private String code;
    private String message;
    private T data;

    public boolean isSuccess(){
        return getCode().equals(MainUtil.SUCCESS_CODE);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
