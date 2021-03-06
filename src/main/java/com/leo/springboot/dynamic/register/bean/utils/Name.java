package com.leo.springboot.dynamic.register.bean.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author: leo wang
 * @date: 2022-04-15
 * @description:
 **/
@Target({ElementType.METHOD,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Name {
    String value() default "";
}
