package org.example.pages.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.example.pages.cart.CartPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CompletePage extends BasePage {
    private Logger log = LogManager.getLogger(CompletePage.class);
    private final By message = By.xpath("//h2[@data-test='complete-header']");
    public CompletePage(WebDriver driver) {
        super(driver);
    }

    public WebElement getMessage() {
        log.info("Getting final thank you message");
        return findElement(message);
    }
}
