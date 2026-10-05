package dataProviderTest;

import org.testng.annotations.DataProvider;
import util.ExcelFileManger;
import util.JSONFileManager;

import java.util.List;
import java.util.stream.Stream;

public class DataProviderTest {
//    JSONFileManager jsonFileManager = new JSONFileManager("src/main/resources/allData.json");
//    @DataProvider(name = "wrongCredentials")
//    public Object[][] getWrongData(){
//        return new Object[][] {jsonFileManager.getArray("wrongData1").toArray(),
//                               jsonFileManager.getArray("wrongData2").toArray(),
//                               jsonFileManager.getArray("wrongData3").toArray()};
//    }
//    @DataProvider(name = "noDataWithProduct")
//    public Object[][] getNoDataWithProduct(){
//        return new Object[][] {jsonFileManager.getArray("noData1").toArray(),
//                               jsonFileManager.getArray("noData2").toArray()};
//    }
//    @DataProvider(name = "some")
//    public Object[][] getSomeData(){
//        return new Object[][] {jsonFileManager.getArray("someData1").toArray(),
//                               jsonFileManager.getArray("someData2").toArray()};
//    }
//    @DataProvider(name = "multipleProducts")
//    public Object[][] getMultipleProductData(){
//        return new Object[][] {jsonFileManager.getArray("allData1").toArray(),
//                               jsonFileManager.getArray("allData2").toArray()};
//    }

    ExcelFileManger excelFileManger = new ExcelFileManger("src/main/resources/allData.xlsx",List.of("products","invalidLoginData","informationData"));
    @DataProvider(name = "wrongCredentials")
    public Object[][] getWrongData(){
        return new Object[][] {excelFileManger.getRowValues(1,"invalidLoginData").toArray(),
                               excelFileManger.getRowValues(2,"invalidLoginData").toArray(),
                               excelFileManger.getRowValues(3,"invalidLoginData").toArray()};
    }
    @DataProvider(name = "noDataWithProduct")
    public Object[][] getNoDataWithProduct(){
        return new Object[][] {{List.of(excelFileManger.getCellValue(1,0,"products"))},
                               {excelFileManger.getColumnValues(0,"products")}};
    }
    @DataProvider(name = "some")
    public Object[][] getSomeData(){
        return new Object[][] {{List.of(excelFileManger.getCellValue(1,0,"products")),excelFileManger.getCellValue(1,0,"informationData"),excelFileManger.getCellValue(1,1,"informationData")},
                               {excelFileManger.getColumnValues(0,"products"),excelFileManger.getCellValue(1,0,"informationData"),excelFileManger.getCellValue(1,1,"informationData")}};
    }
    @DataProvider(name = "multipleProducts")
    public Object[][] getMultipleProductData(){
        return new Object[][] {Stream.concat(Stream.of(List.of(excelFileManger.getCellValue(1,0,"products"))),excelFileManger.getRowValues(1, "informationData").stream()).toArray(),
                Stream.concat(Stream.of(excelFileManger.getColumnValues(0, "products")),excelFileManger.getRowValues(1, "informationData").stream()).toArray()};
    }
}
