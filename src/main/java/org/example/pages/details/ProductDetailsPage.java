package org.example.pages.details;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ProductDetailsPage extends BasePage {
    private Logger log = LogManager.getLogger(ProductDetailsPage.class);
    private final By backToProducts = By.id("back-to-products");
    private final By name = By.xpath("//div[@data-test='inventory-item-name']");
    private final By remove = By.id("remove");
    public ProductDetailsPage(WebDriver driver) {
        super(driver);
    }
    public WebElement getBackToProducts() {
        log.info("Getting back to products button");
        return findElement(backToProducts);
    }
    public WebElement getName() {
        log.info("Getting product name");
        return findElement(name);
    }
    public WebElement getRemove() {
        log.info("Getting remove button");
        return findElement(remove);
    }
    public void clickBackToProducts(){
        getBackToProducts().click();
        log.info("back to products button clicked");
    }
}
