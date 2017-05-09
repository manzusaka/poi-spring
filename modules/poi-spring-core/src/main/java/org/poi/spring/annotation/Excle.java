package org.poi.spring.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Created by oldflame on 2017/4/8.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface Excle {

    String name();

    String sheetName() default "sheet_1";

    short sheetIndex() default 1;

    int width() default 20;

    Align align() default Align.GENERAL;

    short font() default 0;
}
