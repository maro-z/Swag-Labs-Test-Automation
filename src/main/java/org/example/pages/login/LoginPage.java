package org.example.pages.login;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private Logger log = LogManager.getLogger(LoginPage.class);
    private final By usernameField = By.id("user-name");
    private final By passwordField = By.id("password");
    private final By loginButton = By.id("login-button");
    private final By errorMessage = By.xpath("//h3[@data-test='error']");
    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public WebElement getUsernameField() {
        log.info("Getting username field");
        return findElement(usernameField);
    }
    public WebElement getPasswordField() {
        log.info("Getting password field");
        return findElement(passwordField);
    }
    public WebElement getLoginButton() {
        log.info("Getting login button");
        return findElement(loginButton);
    }
    public WebElement getErrorMessage(){
        log.info("Getting login error message");
        return findElement(errorMessage);
    }
    public void enterUsername(String username){
        getUsernameField().sendKeys(username);
        log.debug("Entered Username: {}",username);
    }
    public void enterPassword(String password){
        getPasswordField().sendKeys(password);
        log.debug("Entered Password: {}",password);
    }
    public void clickLoginButton(){
        getLoginButton().click();
        log.info("Login button clicked");
    }
}
