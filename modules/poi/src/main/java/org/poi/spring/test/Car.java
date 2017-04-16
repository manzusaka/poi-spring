package org.poi.spring.test;

import org.poi.spring.annotation.Column;
import org.poi.spring.annotation.ExcleWorkBook;

/**
 * Created by oldflame on 2017/4/8.
 */
@ExcleWorkBook(name = "导出文件1")
public class Car {

    @Column(title = "111111")
    public String name;

    @Column(title = "222222")
    private String age;

    private Integer dddd;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Integer getDddd() {
        return dddd;
    }

    public void setDddd(Integer dddd) {
        this.dddd = dddd;
    }
}
