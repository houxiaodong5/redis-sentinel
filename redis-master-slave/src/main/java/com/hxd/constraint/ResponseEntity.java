package com.hxd.constraint;

import org.springframework.context.annotation.Bean;

/**
 *      统一返回体
 * */

public class ResponseEntity<T> {
    private int code;
    private String msg;
    private T data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ResponseEntity{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }


    public ResponseEntity() {
    }

    public ResponseEntity(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public ResponseEntity<T> standardData(int code, String msg, T data){
        this.setCode(code);
        this.setMsg(msg);
        this.setData(data);
        return this;
    }

}
