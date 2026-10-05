package navigationButton;

import baseTest.BaseTest;
import dataProviderTest.DataProviderTest;
import login.LoginTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.cart.CartPage;
import org.example.pages.login.LoginPage;
import org.example.pages.product.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BackHome extends BaseTest {
    private Logger log = LogManager.getLogger(BackHome.class);
    @Test
    public void continueShopping(){
        log.info("starting test for navigation from cart to home");
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        log.info("filling login form");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("opening cart");
        productPage.clickCart();
        Assert.assertTrue(cartPage.getTitleText().contains("Cart")
        ,"Page title is: "+cartPage.getTitleText());
        log.info("navigating back to home");
        cartPage.clickContinueShopping();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("test ended for navigation from cart to home");
    }
}
