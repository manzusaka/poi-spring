package org.poi.spring.filter;

import org.apache.poi.hssf.usermodel.HSSFHeader;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Header;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Hong.LvHang on 2017-04-11.
 */
public class Test {
    public static void main(String[] args) {
        Workbook workbook = new XSSFWorkbook();  // OR new HSSFWorkbook()
        Sheet sheet = workbook.createSheet("Sheet1");
        Map<String, Object> properties = new HashMap<String, Object>();
        //左右
        properties.put(CellUtil.ALIGNMENT, HorizontalAlignment.CENTER);
        //上下
        properties.put(CellUtil.VERTICAL_ALIGNMENT, VerticalAlignment.CENTER);
        properties.put(CellUtil.HIDDEN, true);
        // border around a cell
        properties.put(CellUtil.BORDER_TOP, CellStyle.BORDER_MEDIUM);
        properties.put(CellUtil.BORDER_BOTTOM, CellStyle.BORDER_MEDIUM);
        properties.put(CellUtil.BORDER_LEFT, CellStyle.BORDER_MEDIUM);
        properties.put(CellUtil.BORDER_RIGHT, CellStyle.BORDER_MEDIUM);

        // Give it a color (RED)
        properties.put(CellUtil.TOP_BORDER_COLOR, IndexedColors.RED.getIndex());
        properties.put(CellUtil.BOTTOM_BORDER_COLOR, IndexedColors.RED.getIndex());
        properties.put(CellUtil.LEFT_BORDER_COLOR, IndexedColors.RED.getIndex());
        properties.put(CellUtil.RIGHT_BORDER_COLOR, IndexedColors.RED.getIndex());
        properties.put(CellUtil.FONT, Font.SS_SUB);
        // Apply the borders to the cell at B2
        Row row = sheet.createRow(1);
        Cell cell = row.createCell(1);
        CellUtil.setCellStyleProperties(cell, properties);
        cell.setCellValue("intech inssss");
        // Apply the borders to a 3x3 region starting at D4
        for (int ix=3; ix <= 5; ix++) {
            row = sheet.createRow(ix);
            for (int iy = 3; iy <= 5; iy++) {
                cell = row.createCell(iy);
                CellUtil.setCellStyleProperties(cell, properties);
                cell.setCellValue("哈哈哈");

            }
        }

        try {
            FileOutputStream fileOut = new FileOutputStream("d:/workbook.xlsx");
            workbook.write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
