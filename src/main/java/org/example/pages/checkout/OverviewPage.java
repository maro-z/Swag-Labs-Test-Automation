package org.example.pages.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class OverviewPage extends BasePage {
    private Logger log = LogManager.getLogger(OverviewPage.class);
    private final By title = By.className("title");
    private final By finish = By.id("finish");
    private final By subTotal = By.className("summary_subtotal_label");
    public OverviewPage(WebDriver driver) {
        super(driver);
    }
    public WebElement getTitle() {
        log.info("Getting page's title");
        return findElement(title);
    }
    public WebElement getFinish() {
        log.info("Getting finish button");
        return findElement(finish);
    }
    public WebElement getSubTotal(){
        log.info("Getting subtotal");
        return findElement(subTotal);
    }
    public String getTitleText(){
        log.info("Getting page's text title");
        return getTitle().getText();
    }
    public void clickFinish(){
        getFinish().click();
        log.info("Finish button clicked");
    }
    public double getSubTotalValue(){
        log.info("Getting subtotal value");
        return Double.parseDouble(getSubTotal().getText().replace("Item total: $","").trim());
    }
}
