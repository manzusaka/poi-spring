package org.poi.spring.annotation;

/**
 * Created by Hong.LvHang on 2017-04-13.
 */
public @interface Column {

    String title();

    boolean required() default false;

    String regex() default "";

    int with() default 20;

    String sheetName() default "sheet_1";

    short sheetIndex() default 1;

    Align align() default Align.GENERAL;

    int font() default 12;

    boolean wraptext() default true;

    String defauleValue() default "";
}
