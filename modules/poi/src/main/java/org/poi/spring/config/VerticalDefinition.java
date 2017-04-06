package org.poi.spring.config;

import org.poi.spring.filter.Filter;
import org.springframework.core.convert.converter.Converter;

import java.util.List;

/**
 * Created by oldflame on 2017/4/6.
 */
public class VerticalDefinition {
    /**
     * 属性名称,必须
     */
    private String name;
    /**
     * 标题,必须
     */
    private String title;
    /**
     * 是否必须
     */
    private boolean required = false;
    /**
     * 字段转化类 下载使用
     */
    private String convertdown;
    /**
     * 字段转化类 下载使用
     */
    private Converter converterDown;
    /**
     * 字段转化类  上传使用
     */
    private String convertup;
    /**
     * 字段转化类 上传使用
     */
    private Converter converterUp;
    /**
     * 处理拦截  上传
     */
    private String filter;
    /**
     * 处理拦截  上传
     */
    private List<Filter> filters;
    /**
     * 表达式
     */
    private String regex;
    /**
     * 校验失败返回
     */
    private String validatorErrMsg;

    //导出时生效
    /**
     * cell的宽度,此属性不受enableStyle影响,如自己把数值写的过大
     *
     * @see org.apache.poi.ss.usermodel.Sheet
     */
    private Integer columnWidth;
    /**
     * cell对其方式:支持,center,left,right
     */
    private Short align;
    /**
     * 标题cell背景色
     *
     * @see org.apache.poi.ss.usermodel.IndexedColors
     */
    private Short titleBgColor;
    /**
     * 标题cell字体色
     *
     * @see org.apache.poi.ss.usermodel.IndexedColors
     */
    private Short titleFountColor;

    /**
     * 当值为空时,字段的默认值
     */
    private String defaultValue;
}
