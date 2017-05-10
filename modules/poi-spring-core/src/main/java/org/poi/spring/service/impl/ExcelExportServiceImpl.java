package org.poi.spring.service.impl;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.poi.spring.ReflectUtil;
import org.poi.spring.component.ExcleContext;
import org.poi.spring.component.ExcleConverter;
import org.poi.spring.config.ColumnDefinition;
import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.poi.spring.exception.ExcelException;
import org.poi.spring.service.ExcelExportService;
import org.poi.spring.service.result.ExcelExportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Hong.LvHang on 2017-05-08.
 */
@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Autowired
    private ExcleContext excleContext;

    @Autowired
    private ExcleConverter excleConverter;


    @Override
    public ExcelExportResult createExcel(List<?> beans) {
        ExcelExportResult exportResult = null;
        if (CollectionUtils.isNotEmpty(beans)) {
            Class<?> beanClass = beans.get(0).getClass();
            if (!excleContext.exists(beanClass)) {
                throw new ExcelException("未找到匹配的模板");
            }
            //从注册信息中获取Bean信息
            ExcelWorkBookBeandefinition excelWorkBookBeandefinition = excleContext.getExcelWorkBookBeandefinition(beanClass);

            //创建表格
            exportResult = doCreateExcel(excelWorkBookBeandefinition, beans);
        }
        return exportResult;
    }

    private ExcelExportResult doCreateExcel(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, List<?> beans) {
        Workbook workbook = new SXSSFWorkbook();
        //直接使用sheetname  在初始化的时候已经设置了名字
        Sheet sheet = workbook.createSheet(excelWorkBookBeandefinition.getSheetName());

        Row titleRow = createTitle(excelWorkBookBeandefinition, sheet, workbook);
        //如果listBean不为空,创建数据行
        if (beans != null) {
            createRows(excelWorkBookBeandefinition, sheet, beans);
        }
        ExcelExportResult exportResult = new ExcelExportResult(excelWorkBookBeandefinition, sheet, workbook, this);
        return exportResult;
    }

    /**
     * excle标题
     *
     * @param excelWorkBookBeandefinition
     * @param sheet
     * @param workbook
     * @return
     */
    private Row createTitle(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, Sheet sheet, Workbook workbook) {
        //标题索引号
        int titleIndex = sheet.getPhysicalNumberOfRows();
        Row titleRow = sheet.createRow(titleIndex);
        List<ColumnDefinition> columnDefinitions = excelWorkBookBeandefinition.getColumnDefinitions();
        for (int i = 0; i < columnDefinitions.size(); i++) {
            ColumnDefinition columnDefinition = columnDefinitions.get(i);
            //            if (columnDefinition.getColumnWidth() != null) {
            //                sheet.setColumnWidth(i, columnDefinition.getColumnWidth());
            //            }
            Cell cell = titleRow.createCell(i);
            //            CellUtil.setCellStyleProperties(cell, columnDefinition.getProperties());
            //excleConverter.canConvert(String.class, columnDefinition.getTitle().getClass());
            cell.setCellValue(columnDefinition.getTitle());
        }
        return titleRow;
    }

    @Override
    public void createRows(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, Sheet sheet, List<?> beans) {
        int startRow = sheet.getPhysicalNumberOfRows();
        for (int i = 0; i < beans.size(); i++) {
            int rowNum = startRow + i;
            Row row = sheet.createRow(rowNum);
            createRow(excelWorkBookBeandefinition, row, beans.get(i), rowNum);
        }
    }

    private void createRow(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, Row row, Object bean, int rowNum) {
        List<ColumnDefinition> columnDefinitions = excelWorkBookBeandefinition.getColumnDefinitions();
        for (int i = 0; i < columnDefinitions.size(); i++) {
            ColumnDefinition columnDefinition = columnDefinitions.get(i);
            String name = columnDefinition.getName();
            Object value = ReflectUtil.getProperty(bean, name);
            if (!excleConverter.canConvertString(value.getClass())) {
                throw new ExcelException("无法转换成字符串,字段名name" + name);
            }
            String valueStr = excleConverter.convert(value, String.class);
            Cell cell = row.createCell(i);
            //            CellUtil.setCellStyleProperties(cell, columnDefinition.getProperties());
            cell.setCellValue(valueStr);
        }
    }
}
