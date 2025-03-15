package com.utilities;

import lombok.extern.log4j.Log4j2;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;

@Log4j2
public class ExcelProcessManager {

    static FileInputStream inputStream;
    static XSSFWorkbook workbook;
    static XSSFSheet sheet;
    static Iterator<Row> rows;
    static Row row;
    static Iterator<Cell> cells;
    static Cell cell;

    public static ArrayList<String> excelRowData;

    private static void setUpExcelOperation() {
        try {
            String path = Paths.get(System.getProperty("user.dir"), "src", "test", "resources", "testData", "ProductNamesData.xlsx").toString();

            inputStream = new FileInputStream(path);
            workbook = new XSSFWorkbook(inputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        int sheetCount = workbook.getNumberOfSheets();
        for (int i = 0; i < sheetCount; i++) {
            sheet = workbook.getSheetAt(i);
//            System.out.println("sheetName = " + sheet.getSheetName());

        }
    }

    public static ArrayList<String> getExcelRowData() {
        setUpExcelOperation();
        excelRowData = new ArrayList<>();

        rows = sheet.rowIterator();
        while (rows.hasNext()) {
            row = rows.next();
            cells = row.cellIterator();
            while (cells.hasNext()) {
                cell = cells.next();
                if(!cell.getStringCellValue().isEmpty()){
                    excelRowData.add(cell.getStringCellValue());
                }
            }
        }
        log.info(excelRowData);
//        System.out.println(excelRowData);
        return excelRowData;
    }

    public static String getData(int row, int column) {
        setUpExcelOperation();
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }

    public static void getExcelColumnData() {
        setUpExcelOperation();
        ArrayList<String> columnData = new ArrayList<>();
        int indexOfColumn = 0;

        rows = sheet.rowIterator();
        row = rows.next();
        cells = row.cellIterator();
        while (cells.hasNext()) {
            cell = cells.next();
            if (cell.getStringCellValue().equalsIgnoreCase("Data2")) {
                indexOfColumn = cell.getColumnIndex();
                break;
            }
        }

        while (rows.hasNext()) {
            row = rows.next();
            columnData.add(row.getCell(indexOfColumn).getStringCellValue());
        }

        log.info(columnData);
//        System.out.println(columnData);
    }
}
