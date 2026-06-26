package com.orangehrm.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelWriterReaderUtility {
	
	private static final String FILE_PATH=System.getProperty("user.dir")+"/src/test/resources/testdata/TargetData.xlsx";
	private static final String SHEET_NAME="TestedData";
	
	public static void writeData (String key,String value) {
		appendKeyValueData(FILE_PATH,SHEET_NAME,key,value);
	}
	
    public static void appendKeyValueData(String filePath, String sheetName,String key,String value) {

        try (FileInputStream fis = new FileInputStream(filePath);
                Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
            }

            // Get next empty row
            int rowNum = sheet.getLastRowNum() + 1;
            
            Map<String, String> data = new LinkedHashMap<>();
            data.put(key, value);

            for (Map.Entry<String, String> entry : data.entrySet()) {

                Row row = sheet.createRow(rowNum++);

                row.createCell(0).setCellValue(entry.getKey());
                row.createCell(1).setCellValue(entry.getValue());
            }

            fis.close();

            try (FileOutputStream fos = new FileOutputStream(filePath)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void clearDataKeepHeader() {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);

            if (sheet != null) {

                int lastRowNum = sheet.getLastRowNum();

                // Remove all rows except the first row (index 0)
                for (int i = lastRowNum; i > 0; i--) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        sheet.removeRow(row);
                    }
                }
            }

            fis.close();
            
            try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
                workbook.write(fos);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static String getValueByKey(String key) {

        try (FileInputStream fis = new FileInputStream(FILE_PATH);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(SHEET_NAME);

            if (sheet == null) {
                return null;
            }

            for (Row row : sheet) {

                if (row.getCell(0) != null &&
                    key.equalsIgnoreCase(row.getCell(0).getStringCellValue())) {

                    return row.getCell(1) != null
                            ? row.getCell(1).getStringCellValue()
                            : null;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
    
}