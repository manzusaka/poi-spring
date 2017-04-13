package org.poi.spring.context;

import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.springframework.beans.BeansException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
        List<ExcelWorkBookBeandefinition> excelWorkBookBeandefinitions =
            (List<ExcelWorkBookBeandefinition>) getApplicationContext().getBean(ExcelWorkBookBeandefinition.class);
        if (!CollectionUtils.isEmpty(excelWorkBookBeandefinitions)) {
            for (ExcelWorkBookBeandefinition beandefinition : excelWorkBookBeandefinitions) {
                excleIds.add(beandefinition.getId());
                excleMap.put(beandefinition.getId(), beandefinition);
            }
        }
    }

}
