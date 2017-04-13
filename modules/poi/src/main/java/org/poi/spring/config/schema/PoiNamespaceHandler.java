package org.poi.spring.config.schema;

import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * Created by oldflame on 2017/4/8.
 */
public class PoiNamespaceHandler extends NamespaceHandlerSupport {

    /**
     * Register the excle {@link ExcleWorkBookBeanDefinitionParser} for config
     */
    public void init() {
        registerBeanDefinitionParser("annotation-driven", new AnnotationDrivenPoiBeanDefinitionParser());
        registerBeanDefinitionParser("excle-work-book", new ExcleWorkBookBeanDefinitionParser(ExcelWorkBookBeandefinition.class));
    }
}
