package org.example.pages;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.product.ProductPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {
    private Logger log = LogManager.getLogger(BasePage.class);
    WebDriver driver;
    WebDriverWait wait;
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }
    public WebElement findElement(By locator){
        log.debug("Finding element: {}",locator);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        log.debug("Waiting for the element: {} to appear",locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElement(locator);
    }
//    public WebElement findElement(By locator,Duration duration){
//        wait = new WebDriverWait(driver, duration);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//        return driver.findElement(locator);
//    }
    public List<WebElement> findElements(By locator){
        log.debug("Finding elements: {}",locator);
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        log.debug("Waiting for the elements: {} to appear",locator);
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return driver.findElements(locator);
    }
}
