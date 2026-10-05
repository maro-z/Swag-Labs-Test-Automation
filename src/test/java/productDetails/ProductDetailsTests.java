package productDetails;

import baseTest.BaseTest;
import dataProviderTest.DataProviderTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.details.ProductDetailsPage;
import org.example.pages.login.LoginPage;
import org.example.pages.product.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import productAdding.AddProducts;

import java.util.List;

public class ProductDetailsTests extends BaseTest {
    private Logger log = LogManager.getLogger(ProductDetailsTests.class);
    @Test(dataProvider="noDataWithProduct",dataProviderClass = DataProviderTest.class)
    public void checkRemoveButtonForAddedElements(List<String> names){
        log.info("starting test for checking remove button for all added products in their page using {} as product names",names);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        ProductDetailsPage productDetailsPage = new ProductDetailsPage(driver);
        log.info("filling login form");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("adding products to page");
        productPage.clickOnMultipleProducts(names);
        Assert.assertEquals(productPage.getCartBadgeNumber(),names.size());
        for (String name : names){
            log.info("opening {} page",name);
            productPage.getProductName(name).click();
            softAssert.assertTrue(productDetailsPage.getRemove().isDisplayed());
            log.info("navigating bck to products page");
            productDetailsPage.clickBackToProducts();
            softAssert.assertEquals(productPage.getTitleText(),"Products");
        }
        softAssert.assertAll();
        log.info("test ended for checking remove button for all added products in their page using {} as product names",names);
    }
}
