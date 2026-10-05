package util;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class CSVFileManager {
    private static Logger log = LogManager.getLogger(CSVFileManager.class);
    private FileReader reader;
    private List<String[]> rows;
    private Map<String,List<String>> ColumnWithRows;
    private CSVParser records;
    private String csvFilePath;
    private FileReader RowReader;
    private Iterable<CSVRecord> RowRecords;

    /**
     * Creates a new instance of the test data CSV reader using the target CSV
     * file path
     *
     * @param csvFilePath target test data CSV file path
     */
    public CSVFileManager(String csvFilePath){
        initializeVariables();
        this.csvFilePath =csvFilePath;
        try {
            reader = new FileReader(csvFilePath);
            records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(reader);
            RowReader = new FileReader(csvFilePath);
            RowRecords = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(RowReader);
            log.info("Reading test data from the following file. [{}]", this.csvFilePath);
        } catch (IOException e) {
            log.error("Couldn't find the desired file. [{}] ", this.csvFilePath, e);
            e.printStackTrace();
        }
    }
    /**
     * Retrieves all rows from the CSV file as a list of string arrays.
     * Each row is represented as an array of strings.
     *
     * @return a list of string arrays, where each array represents a row in the CSV file.
     */
    public List<String[]> getRows(){
        // If we already read the rows, just return them!
        if (this.rows != null && !this.rows.isEmpty()) {
            return this.rows;
        }

        this.rows = new ArrayList<>();
        try {
            for (CSVRecord record : RowRecords) {
                String[] row = new String[record.size()];
                for (int i = 0; i < record.size(); i++) {
                    row[i] = record.get(i);
                }
                this.rows.add(row);
            }
            log.info("Successfully retrieved all rows from [{}].", csvFilePath);
        } catch (Exception e) {
            log.error("Error while retrieving rows: {}", e.getMessage());
        }
        return this.rows;
    }
    /**
     * Retrieves the column names from the CSV file.
     *
     * @return a list of column names as strings.
     */
    public List<String> getColumns(){
        try {
            List<String> columns = new ArrayList<>(records.getHeaderNames());
            log.info("Successfully retrieved column names from [{}].", csvFilePath);
            return columns;
        } catch (Exception e) {
            log.error("Error while retrieving columns: {}", e.getMessage());
            return Collections.emptyList();
        }
    }
    /**
     * Maps each column name to its corresponding list of row data.
     *
     * @return a map where keys are column names and values are lists of column data.
     */
    public Map<String, List<String>> getColumnsWithData() {
        // If we already mapped the columns, just return the map!
        if (this.ColumnWithRows != null && !this.ColumnWithRows.isEmpty()) {
            return this.ColumnWithRows;
        }

        this.ColumnWithRows = new HashMap<>();
        try {
            List<String[]> rows = getRows();
            List<String> columns = getColumns();
            for (int i = 0; i < columns.size(); i++) {
                List<String> columnData = new ArrayList<>();
                for (String[] row : rows) {
                    if (i < row.length) {
                        columnData.add(row[i]);
                    }
                }
                this.ColumnWithRows.put(columns.get(i), columnData);
            }
            log.info("Your data has been mapped successfully: {}", this.ColumnWithRows);
        } catch (Exception e) {
            log.error("Error while mapping columns with data: {}", e.getMessage());
        }
        return this.ColumnWithRows;
    }
    /**
     * Retrieves all data for a specific column.
     *
     * @param ColumnName the name of the column.
     * @return a list of strings containing the column data, or an empty list if an error occurs.
     */
    public List<String> GetSpecificColumnData(String ColumnName){
        try {
            // Call it exactly once and store it in a variable
            List<String> columnData = getColumnsWithData().get(ColumnName);
            log.info("Get Specific Column Data worked successfully: {} and column: {}", columnData, ColumnName);
            return columnData;
        } catch (Exception e) {
            log.error("Error while retrieving data for column: {}. {}", ColumnName, e.getMessage());
            return Collections.emptyList();
        }
    }
    /**
     * Retrieves a specific cell's data based on row number and column name.
     *
     * @param RowNum the 0-based index of the row.
     * @param ColumnName the name of the column.
     * @return the data in the specified cell, or null if an error occurs.
     */
    public String getCellData(int RowNum, String ColumnName) {
        try {
            String[] row = getRows().get(RowNum);
            List<String> columns = getColumns();
            return row[columns.indexOf(ColumnName)];
        } catch (Exception e) {
            log.error("Error while retrieving cell data for Row: {}, Column: {}. {}", RowNum, ColumnName, e.getMessage());
            return null;
        }
    }
    /**
     * Retrieves the total count of cells in a specific column.
     *
     * @param columnName the name of the column.
     * @return the number of cells in the column, or 0 if an error occurs.
     */
    public int getCellCount(String columnName) {
        try {
            log.info("Successfully retrieved cell count of column : {} from [{}].", columnName, csvFilePath);
            return getColumnsWithData().get(columnName).size();
        } catch (Exception e) {
            log.error("Error calculating count for column: {}. {}", columnName, e.getMessage());
            return 0;
        }
    }
    /**
     * Retrieves the total count of cells in a specific column.
     *
     * @param columnIndex the name of the column.
     * @return the number of cells in the column, or 0 if an error occurs.
     */
    public int getCellCount(int columnIndex) {
        try {
            log.info("Successfully retrieved cell count of column : "+getColumns().get(columnIndex)+" from ["+csvFilePath+"].");
            return getColumnsWithData().get(getColumns().get(columnIndex)).size();
        } catch (Exception e) {
            log.error("Error calculating count for column: {}. {}", getColumns().get(columnIndex), e.getMessage());
            return 0;
        }
    }
    public ArrayList<String> getMostRepeatedValueInColumn(String columnName){
        log.debug("getting the most repeated value in column: {}",columnName);
        List<String> data = GetSpecificColumnData(columnName);
        HashMap<String,Integer> counts = new HashMap<>();
        for (String cell:data){
            if (counts.containsKey(cell.toLowerCase().trim())){
                log.debug("increasing the value for: {}",cell);
                int newCount = counts.get(cell.toLowerCase().trim()) + 1 ;
                counts.replace(cell.toLowerCase().trim(),newCount);
            }
            else {
                log.debug("found: {} for the first time",cell);
                counts.put(cell.toLowerCase().trim(),1);
            }
        }
        ArrayList<String> mostRepeated = new ArrayList<>();
        int highest = -1;
        for (Map.Entry<String,Integer> entry : counts.entrySet()){
            String key = entry.getKey();
            int value = entry.getValue();
            if (value==highest){
                mostRepeated.add(key);
                log.debug("{} added to the highest",key);
                continue;
            }
            if (value>highest){
                highest=value;
                mostRepeated.clear();
                mostRepeated.add(key);
                log.debug("{} is the current highest",key);
            }
        }
        return mostRepeated;
    }
    private void initializeVariables() {
        reader = null;
        RowReader = null;
        records =null;
        RowRecords=null;
        rows = null;
        ColumnWithRows=null;
        csvFilePath = "";
    }
}
