package com.springboot.validate.springbootvalidateexception.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotNull(message = "姓名不能为空")
    private String name;

    //{user.name.notblank} 从 ValidationMessages.properties 获取到的
    @NotNull(message = "{user.name.notblank}")
    private Integer age;

    @NotNull(message = "编号不能为空")
    private Integer bianhao;

    @NotNull(message = "地址不能为空")
    private String address;

}
