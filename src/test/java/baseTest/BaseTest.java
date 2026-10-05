package baseTest;

import driverFactory.DriverFactory;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.asserts.SoftAssert;
import util.ConfigHandler;
import util.ExcelFileManger;
import util.JSONFileManager;
import util.ScreenShot;

import java.io.File;
import java.io.IOException;

public class BaseTest {
    private Logger log = LogManager.getLogger(BaseTest.class);
    public static WebDriver driver;
    public SoftAssert softAssert;
    public ConfigHandler configHandler;
    public JSONFileManager jsonFileManager;
    public ExcelFileManger excelFileManger;
    @BeforeMethod
    public void setup(){
        log.info("Initializing Excel Manger");
        excelFileManger = new ExcelFileManger("src/main/resources/product.xlsx","Sheet1");
        log.info("Initializing JSON Manger");
        jsonFileManager = new JSONFileManager("src/main/resources/allData.json");
        log.info("Initializing Config Handler");
        configHandler = new ConfigHandler("src/main/resources/config.properties");
        log.info("Initializing Soft assert");
        softAssert = new SoftAssert();
        log.info("Getting Web driver");
        driver = DriverFactory.getWebDriver(configHandler.getValue("browserName"));
        log.info("Opening the window");
        driver.get(configHandler.getValue("url"));
        log.info("Maximizing the window");
        driver.manage().window().maximize();
    }
    @AfterMethod
    public void failedTestCase(ITestResult result) throws IOException {
        if (result.getStatus()==ITestResult.FAILURE){
            log.info("test case failed taking a screenshot");
            File image =ScreenShot.takeScreenShot(driver);
            //TODO: fix the null test name
            Allure.addAttachment("Failure screenshot for TC: "+result.getTestName(),"image/png", FileUtils.openInputStream(image),"png");
        }
    }
    @AfterMethod
    public void tearDown(){
        log.info("Quitting the diver");
        DriverFactory.quitDriver(configHandler.getValue("browserName"));
        driver=null;

    }
}
