package org.poi.spring.service.result;

import java.util.List;

/**
 * Excel导入结果
 */
public class ExcelImportResult {

    /**
     * JavaBean集合,从标题行下面解析的数据
     */
    private List<?> beans;

    public ExcelImportResult(List<?> beans) {
        this.beans = beans;
    }

    public List<?> getBeans() {
        return beans;
    }

    public void setBeans(List<?> beans) {
        this.beans = beans;
    }
}
