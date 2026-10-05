package invalidTests;

import baseTest.BaseTest;
import dataProviderTest.DataProviderTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.cart.CartPage;
import org.example.pages.checkout.InformationPage;
import org.example.pages.login.LoginPage;
import org.example.pages.product.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class InvalidTests extends BaseTest {
    private Logger log = LogManager.getLogger(InvalidTests.class);
    @Test
    public void negativeFlow(){
        log.info("Starting negative flow test");
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        InformationPage informationPage = new InformationPage(driver);
        log.info("Filling login form");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("opening cart");
        productPage.clickCart();
        Assert.assertTrue(cartPage.getTitleText().contains("Cart"));
        log.info("clicking checkout");
        cartPage.clickCheckout();
        Assert.assertFalse(informationPage.getTitleText().contains("Cart"));
    }
    @Test(dataProvider = "noDataWithProduct",dataProviderClass = DataProviderTest.class)
    public void noData(List<String> names){
        log.info("Starting the test to checkout with no data in information with {} products",names);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        InformationPage informationPage = new InformationPage(driver);
        log.info("Filling login form");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("Adding products to cart");
        productPage.clickOnMultipleProducts(names);
        log.info("making sure the number of items in carts matches");
        Assert.assertEquals(productPage.getCartBadgeNumber(),names.size());
        log.info("opening cart");
        productPage.clickCart();
        Assert.assertTrue(cartPage.getTitleText().contains("Cart"));
        Assert.assertEquals(cartPage.getNumberOfCorrectProducts(names),names.size());
        log.info("clicking checkout");
        cartPage.clickCheckout();
        Assert.assertTrue(informationPage.getTitleText().contains("Information"));
        log.info("Clicking continue button");
        informationPage.clickContinueButton();
        Assert.assertTrue(informationPage.getErrorMessage().isDisplayed());
    }
    @Test(dataProvider = "some",dataProviderClass = DataProviderTest.class)
    public void someData(List<String> names,String fName,String lName){
        log.info("Starting the test to checkout with some data in information with {} products and {} first name and {} last name",names,fName,lName);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        InformationPage informationPage = new InformationPage(driver);
        log.info("Filling login form");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("Adding products to cart");
        productPage.clickOnMultipleProducts(names);
        log.info("making sure the number of items in carts matches");
        Assert.assertEquals(productPage.getCartBadgeNumber(),names.size());
        log.info("opening cart");
        productPage.clickCart();
        Assert.assertTrue(cartPage.getTitleText().contains("Cart"));
        Assert.assertEquals(cartPage.getNumberOfCorrectProducts(names),names.size());
        log.info("clicking checkout");
        cartPage.clickCheckout();
        Assert.assertTrue(informationPage.getTitleText().contains("Information"));
        log.info("filling some data in the form");
        informationPage.enterFirstName(fName);
        informationPage.enterLastName(lName);
        log.info("Clicking continue button");
        informationPage.clickContinueButton();
        Assert.assertTrue(informationPage.getErrorMessage().getText().toLowerCase().contains("postal"));
    }
}
