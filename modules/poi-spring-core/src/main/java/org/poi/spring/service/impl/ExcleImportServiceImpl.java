package org.poi.spring.service.impl;

import org.apache.poi.hssf.util.CellReference;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.poi.spring.CellReferenceWapper;
import org.poi.spring.ReflectUtil;
import org.poi.spring.component.ExcleContext;
import org.poi.spring.component.ExcleConverter;
import org.poi.spring.config.ColumnDefinition;
import org.poi.spring.config.ExcelWorkBookBeandefinition;
import org.poi.spring.exception.ExcelException;
import org.poi.spring.service.ExcelImportService;
import org.poi.spring.service.result.ExcelImportResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Hong.LvHang on 2017-05-09.
 */
@Service
public class ExcleImportServiceImpl implements ExcelImportService {
    @Autowired
    private ExcleContext excleContext;

    @Autowired
    private ExcleConverter excleConverter;

    /**
     * 格式化信息
     */
    private DataFormatter formatter = new DataFormatter();

    @Override
    public ExcelImportResult readExcel(Class<?> beanClass, int titleIndex, InputStream excelStream, Integer sheetIndex, boolean multivalidate) {
        if (!excleContext.exists(beanClass)) {
            throw new ExcelException("未找到匹配的模板");
        }
        //从注册信息中获取Bean信息
        ExcelWorkBookBeandefinition excelWorkBookBeandefinition = excleContext.getExcelWorkBookBeandefinition(beanClass);
        return doReadExcel(excelWorkBookBeandefinition, titleIndex, excelStream, sheetIndex, multivalidate);
    }

    private ExcelImportResult doReadExcel(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, int titleIndex, InputStream excelStream, Integer sheetIndex, boolean multivalidate) {
        ExcelImportResult result = null;
        Workbook workbook = null;

        try {
            workbook = WorkbookFactory.create(excelStream);

            //读取sheet,sheetIndex参数优先级大于ExcelDefinition配置sheetIndex
            Sheet sheet = workbook.getSheetAt(sheetIndex == null ? excelWorkBookBeandefinition.getSheetIndex() : sheetIndex);
            //标题之前的数据处理
            //            List<List<Object>> header = readHeader(excelWorkBookBeandefinition, sheet, titleIndex);
            //获取标题
            Map<Integer, CellReferenceWapper> titleWapperMap = readTitle(excelWorkBookBeandefinition, sheet, titleIndex);
            //获取Bean
            List<Object> listBeans = readRows(excelWorkBookBeandefinition, sheet, titleIndex, titleWapperMap, multivalidate);

            result=new ExcelImportResult(listBeans);

        } catch (IOException | InvalidFormatException e) {
            e.printStackTrace();
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    private Map<Integer, CellReferenceWapper> readTitle(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, Sheet sheet, int titleIndex) {
        Map<Integer, CellReferenceWapper> map = new HashMap<>();
        List<ColumnDefinition> columnDefinitions = excelWorkBookBeandefinition.getColumnDefinitions();
        // 获取Excel标题数据
        Row row = sheet.getRow(titleIndex);
        for (Cell cell : row) {
            //cell封装对象，可以获取cell的相关信息
            CellReference cellRef = new CellReference(row.getRowNum(), cell.getColumnIndex());
            String text = formatter.formatCellValue(cell);
            String fieldName = null;
            for (ColumnDefinition columnDefinition : columnDefinitions) {
                if (columnDefinition.getTitle().equals(text)) {
                    fieldName = columnDefinition.getName();
                    break;
                }

            }
            map.put(cell.getColumnIndex(), new CellReferenceWapper(cellRef, text, fieldName));
        }
        return map;
    }

    private <T> List<T> readRows(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, Sheet sheet, int titleIndex, Map<Integer, CellReferenceWapper> titleWapperMap, boolean multivalidate) {
        int rowNum = sheet.getLastRowNum();
        //读取数据的总共次数
        int totalNum = rowNum - titleIndex;
        List<T> listBean = new ArrayList<T>(totalNum);
        for (int i = titleIndex + 1; i <= rowNum; i++) {
            Row row = sheet.getRow(i);
            Object bean = readRow(excelWorkBookBeandefinition, row, titleWapperMap, multivalidate);
            listBean.add((T) bean);
        }
        return listBean;
    }

    private Object readRow(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, Row row, Map<Integer, CellReferenceWapper> titleWapperMap, boolean multivalidate) {
        //创建注册时配置的bean类型
        Class<?> dataClass = excelWorkBookBeandefinition.getDataClass();
        Object bean = ReflectUtil.newInstance(dataClass);
        for (Cell cell : row) {
            CellReferenceWapper titleWapper = titleWapperMap.get(cell.getColumnIndex());
            Class<?> fieldClass = ReflectUtil.getPropertyType(bean, titleWapper.getFieldName());
            String text = formatter.formatCellValue(cell);
            if (!excleConverter.canConvert(TypeDescriptor.valueOf(String.class), TypeDescriptor.valueOf(fieldClass))) {
                throw new ExcelException("字符串无法转换成对象值field=" + titleWapper.getFieldName());
            }
            ReflectUtil.setProperty(bean, titleWapper.getFieldName(), excleConverter.convert(text, fieldClass));
        }
        return bean;
    }

    private List<List<Object>> readHeader(ExcelWorkBookBeandefinition excelWorkBookBeandefinition, Sheet sheet, int titleIndex) {
        return null;
    }
}
