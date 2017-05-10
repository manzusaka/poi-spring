package org.poi.spring;


import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;
import org.poi.spring.exception.ExcelException;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;
import org.springframework.util.TypeUtils;

import java.util.Date;

/**
 * 反射工具类
 *
 * @author lisuo
 */
public abstract class ReflectUtil {

    /**
     * 反射无参创建对象
     *
     * @param clazz
     * @return
     */
    public static <T> T newInstance(Class<T> clazz) {
        return BeanUtils.instantiate(clazz);
    }

    /**
     * 获取属性,忽略NestedNullException
     *
     * @param bean
     * @param name
     * @return
     */
    public static Object getProperty(Object bean, String name) {
        try {
            return PropertyUtils.getProperty(bean, name);
        } catch (NestedNullException ignore) {
            return null;
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }

    /**
     * 获取属性类型,忽略NestedNullException
     *
     * @param bean
     * @param name
     * @return
     */
    public static Class<?> getPropertyType(Object bean, String name) {
        try {
            return PropertyUtils.getPropertyType(bean, name);
        } catch (NestedNullException ignore) {
            return null;
        } catch (Exception e) {
            throw new ExcelException(e);
        }
    }


    /**
     * 设置Bean属性,类型自动转换,如果涉及日期转换,只支持long类型或者long值的字符串
     *
     * @param bean
     * @param name
     * @param value
     * @throws ExcelException
     */
    public static void setProperty(Object bean, String name, Object value) {
        if (value != null) {
            try {
                PropertyUtils.setProperty(bean, name, value);
            } catch (Exception e) {
                throw new ExcelException(e);
            }
        }
    }

}
