package org.poi.spring.component;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.support.DefaultConversionService;

/**
 * 类型转换
 * Created by Hong.LvHang on 2017-05-08.
 */

public class DefaultExcleConverter implements ExcleConverter {
    private final ConversionService conversionService;

    public DefaultExcleConverter() {
        this.conversionService = new DefaultConversionService();
    }

    @Override
    public boolean canConvertString(Class<?> sourceType) {
        return canConvert(TypeDescriptor.valueOf(sourceType), TypeDescriptor.valueOf(String.class));
    }

    @Override
    public boolean canConvert(TypeDescriptor sourceType, TypeDescriptor targetType) {
        return this.conversionService.canConvert(sourceType, targetType);
    }

    @Override
    public <T> T convert(Object source, Class<T> targetType) {
        return this.conversionService.convert(source, targetType);
    }
}
