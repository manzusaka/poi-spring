package org.poi.spring.config;

import java.util.ArrayList;
import java.util.List;

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
     * 导出时,sheet页所有的默认列宽,可以不设置
     */
    private Integer defaultColumnWidth;

    /**
     * 导出时,cell默认对其方式:支持,center,left,right
     */
    private Short defaultAlign;


    /**
     * Excel 文件sheet索引，默认为0即，第一个
     */
    private int sheetIndex = 0;

    /**
     * Field属性的全部定义
     */
    private List<ColumnDefinition> columnDefinitions = new ArrayList<ColumnDefinition>();

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

    public Integer getDefaultColumnWidth() {
        return defaultColumnWidth;
    }

    public void setDefaultColumnWidth(Integer defaultColumnWidth) {
        this.defaultColumnWidth = defaultColumnWidth;
    }


    public Short getDefaultAlign() {
        return defaultAlign;
    }

    public void setDefaultAlign(Short defaultAlign) {
        this.defaultAlign = defaultAlign;
    }

    public int getSheetIndex() {
        return sheetIndex;
    }

    public void setSheetIndex(int sheetIndex) {
        this.sheetIndex = sheetIndex;
    }
}
