package org.poi.spring.config.schema;

import org.poi.spring.annotation.ExcleAnnotationConfigurer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by oldflame on 2017/4/8.
 */
public class AnnotationDrivenPoiBeanDefinitionParser implements BeanDefinitionParser {
    private static final String EXCLE_REGISTOR_BEAN_NAME = ExcleAnnotationConfigurer.class.getName();

    private static final Logger logger = LoggerFactory.getLogger(AnnotationDrivenPoiBeanDefinitionParser.class);

    private final Class<?> beanClass = null;

    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        Object source = parserContext.extractSource(element);
        RootBeanDefinition excleAnnotationDef = new RootBeanDefinition(ExcleAnnotationConfigurer.class);
        excleAnnotationDef.setSource(source);
        excleAnnotationDef.setRole(BeanDefinition.ROLE_INFRASTRUCTURE);
        parserContext.getRegistry().registerBeanDefinition(EXCLE_REGISTOR_BEAN_NAME, excleAnnotationDef);
        return null;
    }
}
