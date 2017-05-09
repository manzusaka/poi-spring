package org.poi.spring;

import org.apache.commons.collections4.CollectionUtils;
import org.poi.spring.exception.ExcelException;

import java.util.List;

/**
 * 断言工具类
 */
public abstract class Assert {

    public static void hasNoColumn(String name, List<?> list) {
        if (CollectionUtils.isEmpty(list)) {
            throw new ExcelException("模版" + name + "设置不符合要求，未设置列属性");
        }
    }

}
