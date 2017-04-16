package org.poi.spring.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by oldflame on 2017/4/6.
 */
public class ExcelWorkBookBeandefinition {

    /**
     * ID,必须
     */
    private String id;

    /**
     * 全类名,必须
     */
    private String excleName;

    /**
     * Class信息
     */
    private Class<?> dataClass;

    /**
     * 导出时,sheet页的名称,可以不设置
     */
    private String sheetName;

    /**
     * Excel 文件sheet索引，默认为0即，第一个
     */
    private int sheetIndex = 1;

    private int columnWidth;

    /**
     * 参数信息
     */
    private Map<String, Object> defaultProperties;

    /**
     * Field属性的全部定义
     */
    private List<ColumnDefinition> columnDefinitions = new ArrayList<>();

    public int getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(int columnWidth) {
        this.columnWidth = columnWidth;
    }

    public Map<String, Object> getDefaultProperties() {
        return defaultProperties;
    }

    public void setDefaultProperties(Map<String, Object> defaultProperties) {
        this.defaultProperties = defaultProperties;
    }

    public String getSheetName() {
        return sheetName;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public List<ColumnDefinition> getColumnDefinitions() {
        return columnDefinitions;
    }

    public void setColumnDefinitions(List<ColumnDefinition> columnDefinitions) {
        this.columnDefinitions = columnDefinitions;
    }

    public Class<?> getDataClass() {
        return dataClass;
    }

    public void setDataClass(Class<?> dataClass) {
        this.dataClass = dataClass;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExcleName() {
        return excleName;
    }

    public void setExcleName(String excleName) {
        this.excleName = excleName;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
}
