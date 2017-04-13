package org.poi.spring.config.schema;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

/**
 * Created by oldflame on 2017/4/8.
 */
public class AnnotationDrivenPoiBeanDefinitionParser extends AbstractBeanDefinitionParser {

    private static final Logger logger = LoggerFactory.getLogger(AnnotationDrivenPoiBeanDefinitionParser.class);

    private final Class<?> beanClass = null;
    

    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        return null;
    }
}
