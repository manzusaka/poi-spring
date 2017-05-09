package org.poi.spring.component;

import org.poi.spring.PoiConstant;
import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by oldflame on 2017/4/8.
 */
public class ExcleContext extends ApplicationObjectSupport {

    private final Set<String> excleIds = new HashSet<>();

    private final Map<String, ExcelWorkBookBeandefinition> excleMap = new HashMap<>();

    @Override
    protected void initApplicationContext() throws BeansException {
        String[] excleBeans = getApplicationContext().getBeanNamesForType(ExcelWorkBookBeandefinition.class);
        if (excleBeans != null && excleBeans.length > 0) {
            for (String beanName : excleBeans) {
                ExcelWorkBookBeandefinition excleDef = (ExcelWorkBookBeandefinition) getApplicationContext().getBean(beanName);
                excleIds.add(excleDef.getId());
                excleMap.put(excleDef.getId(), excleDef);
            }
        }
    }

    public boolean exists(Class<?> targetClass) {
        return excleIds.contains(targetClass.getName() + PoiConstant.CLASS_NAME_SUFFIX);
    }

    public ExcelWorkBookBeandefinition getExcelWorkBookBeandefinition(Class<?> targetClass) {
        return excleMap.get(targetClass.getName() + PoiConstant.CLASS_NAME_SUFFIX);
    }
}
