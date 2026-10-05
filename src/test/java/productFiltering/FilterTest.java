package productFiltering;

import baseTest.BaseTest;
import dataProviderTest.DataProviderTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.login.LoginPage;
import org.example.pages.product.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import productDetails.ProductDetailsTests;

public class FilterTest extends BaseTest {
    private Logger log = LogManager.getLogger(FilterTest.class);
    @Test
    public void checkAlphabeticalFilter(){
        log.info("starting test to ensure alphabetical filter works");
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        log.info("filling login data");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("applying the filter");
        productPage.setFilterAlphabetical();
        Assert.assertTrue(productPage.isAlphabeticallyOrdered());
        log.info("test ended for ensure alphabetical filter works");
    }
}
