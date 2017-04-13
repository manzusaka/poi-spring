package org.poi.spring.config;

import org.poi.spring.filter.Filter;
import org.springframework.core.convert.converter.Converter;

import java.util.List;
import java.util.Map;

/**
 * Created by oldflame on 2017/4/6.
 */
public class ColumnDefinition {
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
     * 当值为空时,字段的默认值
     */
    private String defaultValue;

    /**
     * 参数信息
     */
    private Map<String, Object> properties;

    public Map<String, Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String, Object> properties) {
        this.properties = properties;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isRequired() {
        return required;
    }

    public void setRequired(boolean required) {
        this.required = required;
    }

    public String getConvertdown() {
        return convertdown;
    }

    public void setConvertdown(String convertdown) {
        this.convertdown = convertdown;
    }

    public Converter getConverterDown() {
        return converterDown;
    }

    public void setConverterDown(Converter converterDown) {
        this.converterDown = converterDown;
    }

    public String getConvertup() {
        return convertup;
    }

    public void setConvertup(String convertup) {
        this.convertup = convertup;
    }

    public Converter getConverterUp() {
        return converterUp;
    }

    public void setConverterUp(Converter converterUp) {
        this.converterUp = converterUp;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public List<Filter> getFilters() {
        return filters;
    }

    public void setFilters(List<Filter> filters) {
        this.filters = filters;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getValidatorErrMsg() {
        return validatorErrMsg;
    }

    public void setValidatorErrMsg(String validatorErrMsg) {
        this.validatorErrMsg = validatorErrMsg;
    }

    public Integer getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(Integer columnWidth) {
        this.columnWidth = columnWidth;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

}
