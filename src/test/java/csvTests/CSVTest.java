package csvTests;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.Test;
import util.CSVFileManager;

import java.util.List;

public class CSVTest {
    private Logger log = LogManager.getLogger(CSVTest.class);
    private CSVFileManager csvFileManager = new CSVFileManager("src/main/resources/products.csv");
    @Test
    public void csvTest(){
        List<String> temp = csvFileManager.getMostRepeatedValueInColumn("products");
        log.info("Most repeated: {}",temp);
    }
}
