package org.poi.spring.annotation;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

import java.util.ArrayList;

/**
 * Created by Hong.LvHang on 2017-04-12.
 */
public class ExcleScannerConfigurer implements BeanDefinitionRegistryPostProcessor {
    ArrayList<String> cache = new ArrayList<String>();

    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {

//        String[] aaa = beanDefinitionRegistry.getBeanDefinitionNames();
//        BeanDefinition beanDefinition = beanDefinitionRegistry.getBeanDefinition(aaa[0]);
//        beanDefinitionRegistry
//        RootBeanDefinition beanDefinition1 = new RootBeanDefinition();
//        beanDefinition1.setBeanClass(ExcleSheetDefinition.class);
//        beanDefinitionRegistry.registerBeanDefinition("test1111", beanDefinition1);

    }
    @Scope(value = "")
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
