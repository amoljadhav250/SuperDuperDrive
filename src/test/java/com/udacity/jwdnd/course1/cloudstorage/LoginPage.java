package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {


    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    private final WebDriver driver;

    public LoginPage(WebDriver webDriver) {
        this.driver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void login(String username, String password) {
        System.out.println("Entering login credentials");
        /*this.inputUserName.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.submitButton.click();*/
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", inputUserName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", inputPassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
        System.out.println("Loin button clicked");
    }
}
