package org.poi.spring;


import org.apache.commons.beanutils.NestedNullException;
import org.apache.commons.beanutils.PropertyUtils;

/**
 * 反射工具类
 *
 * @author lisuo
 */
public abstract class ReflectUtil {
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
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
    }

}
