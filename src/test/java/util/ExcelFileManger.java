package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import productFiltering.FilterTest;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExcelFileManger {
    private Logger log = LogManager.getLogger(ExcelFileManger.class);
    public XSSFWorkbook workbook;
    public XSSFSheet sheet;
    private HashMap<String,XSSFSheet> sheets;
    public ExcelFileManger(String filepath, String sheetName){
        try {
            log.debug("trying to open excel file: {} with sheet: {}",filepath,sheetName);
            FileInputStream fileInputStream = new FileInputStream(filepath);
            workbook = new XSSFWorkbook(fileInputStream);
            sheet=workbook.getSheet(sheetName);
        }catch (Exception e){
            log.error("error in opening the excel file: {}",e);
        }
    }
    public ExcelFileManger(String filepath, List<String> sheetNames){
        try {
            log.debug("trying to open excel file: {} with sheets: {}",filepath,sheetNames);
            sheets = new HashMap<>();
            FileInputStream fileInputStream = new FileInputStream(filepath);
            workbook = new XSSFWorkbook(fileInputStream);
            for (String sheetName : sheetNames){
                sheets.put(sheetName,workbook.getSheet(sheetName));
            }
        }catch (Exception e){
            log.error("error in opening the excel file: {}",e);
        }
    }
//    public ExcelFileManger(String filepath, int sheetIndex){
//        try {
//            FileInputStream fileInputStream = new FileInputStream(filepath);
//            workbook = new XSSFWorkbook(fileInputStream);
//            sheet=workbook.getSheetAt(sheetIndex);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    public int getRowsCount(String sheetName){
        log.debug("getting number of rows in sheet: {}",sheetName);
        return sheets.get(sheetName).getPhysicalNumberOfRows();
    }
    public int getColumnsCount(String sheetName){
        log.debug("getting number of columns in sheet: {}",sheetName);
        return sheets.get(sheetName).getRow(0).getPhysicalNumberOfCells();
    }
    public String getFormula(int rowIndex, int colIndex,String sheetName){
        log.debug("getting formula for cell: {},{} in sheet: {}",sheetName);
        Cell cell = sheets.get(sheetName).getRow(rowIndex).getCell(colIndex);
        return cell.getCellFormula();
    }
    public String getCellValue(int rowIndex, int colIndex,String sheetName){
        log.debug("getting value in cell: {},{} in sheet: {}",sheetName);
        Cell cell = sheets.get(sheetName).getRow(rowIndex).getCell(colIndex);
        DataFormatter dataFormatter = new DataFormatter();
        return dataFormatter.formatCellValue(cell);
    }
    public List<String> getColumnValues(int colIndex, String sheetName){
        log.debug("getting all values in column: {} in sheet: {}",colIndex,sheetName);
        int numberOfRows = getRowsCount(sheetName);
        ArrayList<String> data = new ArrayList<>();
        for (int i=1;i<numberOfRows;i++){
            data.add(getCellValue(i,colIndex,sheetName));
        }
        return data;
    }
    public List<String> getRowValues(int rowIndex, String sheetName){
        log.debug("getting all values in row: {} in sheet: {}",rowIndex,sheetName);
        int numberOfCols = getColumnsCount(sheetName);
        ArrayList<String> data = new ArrayList<>();
        for (int i=0;i<numberOfCols;i++){
            data.add(getCellValue(rowIndex,i,sheetName));
        }
        return data;
    }

//    public int getRowsCount(){
//        return sheet.getPhysicalNumberOfRows();
//    }
//    public int getColumnsCount(){
//        return sheet.getRow(0).getPhysicalNumberOfCells();
//    }
//    public String getFormula(int rowIndex, int colIndex){
//        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
//        return cell.getCellFormula();
//    }
//    public String getCellValue(int rowIndex, int colIndex){
//        Cell cell = sheet.getRow(rowIndex).getCell(colIndex);
//        DataFormatter dataFormatter = new DataFormatter();
//        return dataFormatter.formatCellValue(cell);
//    }
}
