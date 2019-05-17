package com.springboot.validate.springbootvalidateexception.constant;


import org.springframework.util.StringUtils;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;


@Target( { METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CardValidate.Validator.class)
@Documented
public @interface CardValidate {
    String message() default "invalid phone number";

    //分组
    Class<?>[] groups() default {};

    //负载
    Class<? extends Payload>[] payload() default {};

    class Validator implements ConstraintValidator<CardValidate, String> {
        @Override
        public boolean isValid(String carid, ConstraintValidatorContext arg1){
            if(!StringUtils.isEmpty(carid)){
                //RegexValidateUtils.checkCard(carid)
                //TODO 验证逻辑
                return true;
            }else{
                return true;
            }
        }
    }
}
