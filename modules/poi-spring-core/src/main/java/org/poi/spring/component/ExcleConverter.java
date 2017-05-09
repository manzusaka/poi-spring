package org.poi.spring.component;

import org.springframework.core.convert.TypeDescriptor;

/**
 * Created by Hong.LvHang on 2017-05-08.
 */
public interface ExcleConverter {

    boolean canConvertString(Class<?> sourceType);

    boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType);

    <T> T convert(Object source, Class<T> targetType);

}
