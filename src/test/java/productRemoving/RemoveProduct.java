package productRemoving;

import baseTest.BaseTest;
import dataProviderTest.DataProviderTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.cart.CartPage;
import org.example.pages.login.LoginPage;
import org.example.pages.product.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import productFiltering.FilterTest;

import java.util.List;

public class RemoveProduct extends BaseTest {
    private Logger log = LogManager.getLogger(RemoveProduct.class);
    @Test(dataProvider="noDataWithProduct",dataProviderClass = DataProviderTest.class)
    public void removeProducts(List<String> names){
        log.debug("starting test to ensure remove buttons works using {} products",names);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        log.info("filling login data");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("adding products");
        productPage.clickOnMultipleProducts(names);
        Assert.assertEquals(productPage.getCartBadgeNumber(),names.size());
        log.info("opening cart page");
        productPage.clickCart();
        Assert.assertTrue(cartPage.getTitleText().contains("Cart"));
        log.info("navigating back to products page");
        cartPage.clickContinueShopping();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("removing products");
        productPage.removeProducts(names);
        By badge = productPage.getCartBadgeLocator();
        boolean found = true;
        try {
            WebElement element = driver.findElement(badge);
        }
        catch (org.openqa.selenium.NoSuchElementException e){
            log.error("tried to get the badge after products removal");
            found = false;
        }
        Assert.assertFalse(found);
        log.debug("test ended to ensure remove buttons works using {} products",names);
    }
}
