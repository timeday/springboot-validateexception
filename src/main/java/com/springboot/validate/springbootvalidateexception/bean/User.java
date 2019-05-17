package com.springboot.validate.springbootvalidateexception.bean;

import com.fasterxml.jackson.annotation.JsonView;
import com.springboot.validate.springbootvalidateexception.constant.PhoneVerification;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @JsonView
 *
 * 利用@jsonView注解来实现自定义返回字段
 * 需要三步即可：
 * 第一：用接口来声明多个视图
 * 第二：在值对象的get方法上指定视图
 * 第三：在controller方法上指定视图
 */
@Data
@NoArgsConstructor
public class User {
    //通过不同的接口控制给前端显示不同的数据
    public interface UserInfo{};
    public interface UserDetail extends UserInfo{}; //继承
    @NotBlank(message = "{user.name.notblank}")
    protected String name;
    @Range(max = 150, min = 1, message = "年龄范围应该在1-150内。")
    private Integer age;
    private Integer bianhao;
    private String address;
    @PhoneVerification
    private String phone;
    public User(String name, String password) {
        this.name = name;
        this.password = password;
    }
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, max = 8, message = "密码长度为6-8位。")
    @Pattern(regexp = "[a-zA-Z]*", message = "密码不合法")
    private String password;
    // 如果方法上还有 JsonView(UserInfo.class) 则 给前端展示 这个属性
    @JsonView(UserInfo.class)
    public String getName() {
        return name;
    }
    // 如果方法上还有 JsonView(UserDetail.class) 则 给前端展示 这个属性
    @JsonView(UserDetail.class)
    public String getPassword() {
        return password;
    }

}
