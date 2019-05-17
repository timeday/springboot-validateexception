package com.springboot.validate.springbootvalidateexception.constant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestResultWrapper<T> {

    /**
     * 请求结果
     */

    private T result;
    /**
     * 请求状态码，参考微信API状态码
     */
    private int code;
    /**
     * 请求消息
     */
    private String message;


    //此处没有定义具体的 value值
    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
