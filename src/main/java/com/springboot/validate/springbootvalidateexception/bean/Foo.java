package com.springboot.validate.springbootvalidateexception.bean;

import javax.validation.constraints.Min;

public class Foo {

    @Min(value = 18,groups = {Adult.class})
    private Integer age;

    //用户分组标识
    public interface Adult{

    }
    //用户分组标识
    public interface Minor{

    }
}

