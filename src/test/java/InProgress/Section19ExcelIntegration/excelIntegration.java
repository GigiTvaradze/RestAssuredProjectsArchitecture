package InProgress.Section19ExcelIntegration;

import junit.framework.Assert;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;


public class excelIntegration {
    @Test
    public void excelIntegration() throws IOException {
        FileInputStream fis = null;
        XSSFWorkbook workbook = null;

        try {
            fis = new FileInputStream("/Users/gtv/Desktop/excelIntegration.xlsx");
            workbook = new XSSFWorkbook(fis);
            XSSFSheet sheet = workbook.getSheet("Sheet1");
            XSSFRow row = sheet.getRow(1);

            Assert.assertNotNull("Row should not be null", row);
            XSSFCell cell = row.getCell(1);
            Assert.assertNotNull("Cell should not be null", cell);

            String expectedCellValue = "ExpectedValue";
            String actualCellValue = cell.getStringCellValue();
            Assert.assertEquals(expectedCellValue, actualCellValue);
        } finally {
            // Ensure resources are closed
            if (workbook != null) {
                workbook.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
    }
}

//        FileInputStream fis = new FileInputStream("/Users/gtv/Desktop/excelIntegration.xlsx");
//        XSSFWorkbook workbook = new XSSFWorkbook(fis);
//        XSSFSheet sheet = workbook.getSheet("Sheet1");
//        XSSFRow row = sheet.getRow(1);
//        XSSFCell cell = row.getCell(1);
//
//        String expectedCellValue = "ExpectedValue";
//        String actualCellValue = cell.getStringCellValue();
//        Assert.assertEquals(expectedCellValue, actualCellValue);