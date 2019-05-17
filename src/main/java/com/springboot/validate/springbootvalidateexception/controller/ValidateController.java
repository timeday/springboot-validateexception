package com.springboot.validate.springbootvalidateexception.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.springboot.validate.springbootvalidateexception.bean.Foo;
import com.springboot.validate.springbootvalidateexception.bean.User;
import com.springboot.validate.springbootvalidateexception.bean.UserRequest;
import com.springboot.validate.springbootvalidateexception.constant.RestResultWrapper;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
public class ValidateController {


    /**
     * 结果是空的
     * @param userRequest
     * @return
     */
    @RequestMapping("/getUserOne")
    @JsonView(User.UserInfo.class)
    public RestResultWrapper getUserOne(@Validated UserRequest userRequest){
        User user=new User();
        user.setAddress("ssssssss");
        user.setAge(20);
        user.setBianhao(58588);
        user.setName("天狗食日");
        return new RestResultWrapper(user,0,"成功");
    }

    @RequestMapping("/drink")
    public String drink(@Validated({Foo.Adult.class}) Foo foo, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            for (FieldError fieldError : bindingResult.getFieldErrors()) {
                //...
            }

            return "fail";
        }
        return "success";
    }

    @RequestMapping("/getUser")
    @JsonView(User.UserInfo.class)
    public User getUser(@Validated UserRequest userRequest){
        User user=new User();
        user.setAddress("ssssssss");
        user.setAge(20);
        user.setBianhao(58588);
        user.setName("天狗食日");
        return user;
    }


}
