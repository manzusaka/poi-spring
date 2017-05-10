package org.poi.spring.service.result;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.poi.spring.exception.ExcelException;
import org.poi.spring.service.ExcelExportService;

import java.util.List;

/**
 * Excel导出结果
 */
public class ExcelExportResult {
    private ExcelWorkBookBeandefinition excelWorkBookBeandefinition;
    private Sheet sheet;
    private Workbook workbook;
    private ExcelExportService excelExportService;

    public Workbook getWorkbook() {
        return workbook;
    }

    public ExcelExportResult(ExcelWorkBookBeandefinition excelDefinition, Sheet sheet, Workbook workbook, ExcelExportService excelExportService) {
        super();
        this.excelWorkBookBeandefinition = excelDefinition;
        this.sheet = sheet;
        this.workbook = workbook;
        this.excelExportService = excelExportService;
    }

    /**
     * 追加数据
     *
     * @param beans ListBean
     * @return ExcelExportResult
     */
    public ExcelExportResult append(List<?> beans) {
        try {
            excelExportService.createRows(excelWorkBookBeandefinition, sheet, beans);
        } catch (Exception e) {
            throw new ExcelException(e);
        }
        return this;
    }

    /**
     * 导出完毕,获取WorkBook
     *
     * @return
     */
    public Workbook build() {
        return workbook;
    }

}
