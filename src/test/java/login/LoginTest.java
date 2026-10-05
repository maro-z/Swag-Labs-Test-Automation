package login;
import baseTest.BaseTest;
import dataProviderTest.DataProviderTest;
import invalidTests.InvalidTests;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.login.LoginPage;
import org.example.pages.product.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import util.CSVFileManager;

import java.util.List;

public class LoginTest extends BaseTest{
    private Logger log = LogManager.getLogger(LoginTest.class);

    @Test
    public void validLoginTest(){
        log.info("starting the test to login with valid credentials");
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        log.info("filling the data");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("valid login finish");
    }
    @Test(dataProvider = "wrongCredentials",dataProviderClass = DataProviderTest.class)
    public void invalidLogin(String user,String pass){
        log.info("testing invalid login with: {} and {} as credentials",user,pass);
        LoginPage loginPage = new LoginPage(driver);
        log.info("filling the data");
        loginPage.enterUsername(user);
        loginPage.enterPassword(pass);
        loginPage.clickLoginButton();
        Assert.assertTrue(loginPage.getErrorMessage().isDisplayed());
        log.info("invalid login with: {} and {} as credentials finished",user,pass);
    }

}
