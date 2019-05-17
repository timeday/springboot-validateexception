package com.springboot.validate.springbootvalidateexception.controller;

import com.springboot.validate.springbootvalidateexception.bean.User;
import com.springboot.validate.springbootvalidateexception.constant.RestResultWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;



/**
 *
 *@NotBlank @NotNull 如果在请求的方法上 直接使用 需要在该类上添加
 * @Validated 注解 否则 该验证注解不生效
 *
 * 如果在请求对象中的属性上使用校验 注解 需要在方法请求参数中 该对象之前使用 @Validated 对象 对象名
 *
 */
@RestController
//非对象接收的参数 在类上需要添加该注解
@Validated
public class UserController {
    private static final Logger log = LoggerFactory.getLogger(UserController.class);
    @RequestMapping("/getUserInfo")
    public RestResultWrapper getUserInfo(@NotBlank(message = "地址不能为空！") @RequestParam(name = "address") String Address){
        User user=new User();
        user.setAddress("ssssssss");
        user.setAge(20);
        user.setBianhao(58588);
        user.setName("天狗食日");
        return new RestResultWrapper(user,0,"成功");
    }

}
