package com.springboot.validate.springbootvalidateexception.bean;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @NotNull(message = "姓名不能为空")
    private String name;

    //{user.name.notblank} 从 ValidationMessages.properties 获取到的
    @NotNull(message = "{user.name.notblank}")
    @Range(max = 150, min = 1, message = "年龄范围应该在1-150内。")
    private Integer age;

    @NotNull(message = "编号不能为空")
    private Integer bianhao;

    @NotNull(message = "地址不能为空")
    private String address;

}
