package org.poi.spring;

import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //测试ApplicationContext第一个Beans实例
        ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext.xml");
        ExcelWorkBookBeandefinition beanDefinition = (ExcelWorkBookBeandefinition) context.getBean("infotech");

        System.out.println(beanDefinition);

    }
}
