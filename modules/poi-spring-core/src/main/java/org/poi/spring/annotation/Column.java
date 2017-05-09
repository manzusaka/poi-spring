package org.poi.spring.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by Hong.LvHang on 2017-04-13.
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {

    String title();

    boolean required() default false;

    String regex() default "";

    int width() default 20;

    Align align() default Align.GENERAL;

    short font() default 0;

    boolean wraptext() default true;

    String defauleValue() default "";
}
