package org.example.pages.checkout;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class InformationPage extends BasePage {
    private Logger log = LogManager.getLogger(InformationPage.class);
    private final By title = By.className("title");
    private final By firstNameField = By.id("first-name");
    private final By lastNameField = By.id("last-name");
    private final By postalCodeField = By.id("postal-code");
    private final By continueButton = By.id("continue");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");
    public InformationPage(WebDriver driver) {
        super(driver);
    }
    public WebElement getTitle() {
        log.info("Getting information page title");
        return findElement(title);
    }
    public WebElement getFirstNameField() {
        log.info("Getting first name field");
        return findElement(firstNameField);
    }
    public WebElement getLastNameField() {
        log.info("Getting last name field");
        return findElement(lastNameField);
    }
    public WebElement getPostalCodeField() {
        log.info("Getting postal code field");
        return findElement(postalCodeField);
    }
    public WebElement getContinueButton() {
        log.info("Getting continue button");
        return findElement(continueButton);
    }
    public WebElement getErrorMessage() {
        log.info("Getting page's error message");
        return findElement(errorMessage);
    }

    public String getTitleText(){
        log.info("Getting page's title text");
        return getTitle().getText();
    }
    public void enterFirstName(String name){
        getFirstNameField().sendKeys(name);
        log.debug("Entered first name: {}",name);
    }
    public void enterLastName(String name){
        getLastNameField().sendKeys(name);
        log.debug("Entered last name: {}",name);
    }
    public void enterPostalCode(String code){
        getPostalCodeField().sendKeys(code);
        log.debug("Entered postal code: {}",code);
    }
    public void clickContinueButton(){
        getContinueButton().click();
        log.info("continue button clicked");
    }
}
