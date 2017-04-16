package org.poi.spring;

import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        //测试ApplicationContext第一个Beans实例
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        AbstractApplicationContext abstractApplicationContext = (AbstractApplicationContext) context;
        System.out.println(abstractApplicationContext.getBeanFactory());
        ExcelWorkBookBeandefinition beandefinition = (ExcelWorkBookBeandefinition) context.getBean("org.poi.spring.test.Car.EXCLE");
        Map<String, Object> map = beandefinition.getDefaultProperties();
        for(String key:map.keySet()){
            System.out.println("key=" + key + "---value=" + map.get(key));
        }


    }
}
