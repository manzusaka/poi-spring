package org.poi.spring.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.poi.spring.service.ExcelImportService;
import org.poi.spring.service.result.ExcelImportResult;
import org.poi.spring.test.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;

/**
 * Created by Hong.LvHang on 2017-05-10.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ExcelImportServiceTest {

    @Autowired
    private ExcelImportService excelImportService;

    @Test
    public void createExceltest() throws FileNotFoundException {
        ExcelImportResult excelImportResult = excelImportService.readExcel(Car.class, 0, new FileInputStream("d:/workbook.xlsx"), 0, false);
        List<Car> cars = (List<Car>) excelImportResult.getBeans();
        System.out.println(cars.get(0).getName());
    }
}
