package com.springboot.validate.springbootvalidateexception.constant;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;
import org.hibernate.validator.constraints.Length;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.ReportAsSingleViolation;
import javax.validation.constraints.Null;
import javax.validation.constraints.Pattern;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * @Author zhw 手机号码校验
 * @Description
 * @Date Create in 11:25 2018/5/16
 */
@ConstraintComposition(CompositionType.OR)
//模式
@Pattern(regexp = "1[3|4|5|7|8|6][0-9]\\d{8}")
@Null
@Length(min = 0, max = 16)
@Documented
@Constraint(validatedBy = {})
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RUNTIME)
@ReportAsSingleViolation
public @interface PhoneVerification {
    String message() default "手机号校验错误";

    //分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};
}
