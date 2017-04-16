package org.poi.spring.context;

import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by oldflame on 2017/4/8.
 */
@Service
public class ExcelContext extends ApplicationObjectSupport {

    private static final Set<String> excleIds = new HashSet<>();

    private static final Map<String, ExcelWorkBookBeandefinition> excleMap = new HashMap<>();

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

}
