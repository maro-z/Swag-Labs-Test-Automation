package productAdding;

import baseTest.BaseTest;
import dataProviderTest.DataProviderTest;
import navigationButton.BackHome;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.cart.CartPage;
import org.example.pages.checkout.CompletePage;
import org.example.pages.checkout.InformationPage;
import org.example.pages.checkout.OverviewPage;
import org.example.pages.login.LoginPage;
import org.example.pages.product.ProductPage;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

public class AddProducts extends BaseTest {
    private Logger log = LogManager.getLogger(AddProducts.class);
    @Test(dataProvider="multipleProducts",dataProviderClass = DataProviderTest.class)
    public void multiple(List<String> names,String fName,String lName,String postalCode){
        log.info("testing full flow with {} products",names);
        LoginPage loginPage = new LoginPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);
        InformationPage informationPage = new InformationPage(driver);
        OverviewPage overviewPage = new OverviewPage(driver);
        CompletePage completePage = new CompletePage(driver);
        log.info("filling login form");
        loginPage.enterUsername(configHandler.getValue("username"));
        loginPage.enterPassword(configHandler.getValue("password"));
        loginPage.clickLoginButton();
        Assert.assertEquals(productPage.getTitleText(),"Products");
        log.info("adding products to cart");
        productPage.clickOnMultipleProducts(names);
        Assert.assertEquals(productPage.getCartBadgeNumber(),names.size());
        log.info("opening cart");
        productPage.clickCart();
        Assert.assertTrue(cartPage.getTitleText().contains("Cart"));
        Assert.assertEquals(cartPage.getNumberOfCorrectProducts(names),names.size());
        log.info("clicking checkout");
        cartPage.clickCheckout();
        Assert.assertTrue(informationPage.getTitleText().contains("Information"));
        log.info("filling the data");
        informationPage.enterFirstName(fName);
        informationPage.enterLastName(lName);
        informationPage.enterPostalCode(postalCode);
        informationPage.clickContinueButton();
        log.info("overviewing");
        Assert.assertTrue(overviewPage.getTitleText().contains("Overview"));
        overviewPage.clickFinish();
        Assert.assertTrue(completePage.getMessage().isDisplayed());
    }
}
