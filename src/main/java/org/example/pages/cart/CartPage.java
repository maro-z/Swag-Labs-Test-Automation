package org.example.pages.cart;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.example.pages.login.LoginPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class CartPage extends BasePage {
    private Logger log = LogManager.getLogger(CartPage.class);
    private final By title = By.className("title");
    private final By productNames = By.className("inventory_item_name");
    private final By continueShopping = By.id("continue-shopping");
    private final By checkout = By.id("checkout");
    public CartPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getTitle() {
        log.info("Getting cart page title");
        return findElement(title);
    }
    public List<WebElement> getProductNames() {
        log.info("Getting products name in cart");
        return findElements(productNames);
    }
    public WebElement getContinueShopping() {
        log.info("Getting continue shopping button");
        return findElement(continueShopping);
    }
    public WebElement getCheckout() {
        log.info("Getting checkout button");
        return findElement(checkout);
    }
    public String getTitleText(){
        log.info("Getting text in cart title page");
        return getTitle().getText();
    }
    public int getNumberOfCorrectProducts(List<String>names){
        log.info("Checking the added items to cart");
        log.debug("Items that should be in cart: {}",names.toString());
        int allInCart=0;
        List<WebElement> cartItems = getProductNames();
        for (WebElement e : cartItems) {
            for (String n : names){
                if (e.getText().toLowerCase().contains(n)){
                    log.debug("{} was found",n);
                    allInCart++;
                    break;
                }
            }
        }
        return allInCart;
    }
    public void clickContinueShopping(){
        getContinueShopping().click();
        log.info("Continue shopping button clicked");
    }
    public void clickCheckout(){
        getCheckout().click();
        log.info("Checkout button clicked");
    }
}
