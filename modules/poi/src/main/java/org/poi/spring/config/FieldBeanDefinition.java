package org.poi.spring.config;

/**
 * Created by oldflame on 2017/4/5.
 */
public class FieldBeanDefinition implements Comparable<FieldBeanDefinition> {
    /**
     * 序号，排序规则
     */
    private Integer id;

    /**
     * 属性名称，对应实体字段名,必须
     */
    private String name;
    /**
     * 标题,必须
     */
    private String title;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getColumnWidth() {
        return columnWidth;
    }

    public void setColumnWidth(Integer columnWidth) {
        this.columnWidth = columnWidth;
    }

    public Short getAlign() {
        return align;
    }

    public void setAlign(Short align) {
        this.align = align;
    }

    public Short getTitleBgColor() {
        return titleBgColor;
    }

    public void setTitleBgColor(Short titleBgColor) {
        this.titleBgColor = titleBgColor;
    }

    public Short getTitleFountColor() {
        return titleFountColor;
    }

    public void setTitleFountColor(Short titleFountColor) {
        this.titleFountColor = titleFountColor;
    }

    public int compareTo(FieldBeanDefinition o) {
        return this.id.compareTo(o.getId());
    }
}
