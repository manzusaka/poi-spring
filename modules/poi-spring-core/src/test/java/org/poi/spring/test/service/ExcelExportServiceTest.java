package org.poi.spring.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.poi.spring.service.ExcelExportService;
import org.poi.spring.service.result.ExcelExportResult;
import org.poi.spring.test.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hong.LvHang on 2017-05-09.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ExcelExportServiceTest {
    @Autowired
    private ExcelExportService excelExportService;

    @Test
    public void createExceltest() {
        Car car1 = new Car("info1", "22");
        Car car2 = new Car("info2", "gg");
        Car car3 = new Car("info3", "asf");
        Car car4 = new Car("info4", "aa");
        List<Car> cars = new ArrayList<>();
        cars.add(car1);
        cars.add(car2);
        cars.add(car3);
        cars.add(car4);
        ExcelExportResult result = excelExportService.createExcel(cars);

        try {
            FileOutputStream fileOut = new FileOutputStream("d:/workbook.xlsx");
            result.build().write(fileOut);
            fileOut.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                result.getWorkbook().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
