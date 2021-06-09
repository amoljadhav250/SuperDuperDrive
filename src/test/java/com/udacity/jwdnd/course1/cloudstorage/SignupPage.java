package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement inputFirstName;

    @FindBy(id = "inputLastName")
    private WebElement inputLastName;

    @FindBy(id = "inputUsername")
    private WebElement inputUserName;

    @FindBy(id = "inputPassword")
    private WebElement inputPassword;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    private final WebDriver driver;

    public SignupPage(WebDriver webDriver) {
        this.driver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void signup(String firstName, String lastName, String username, String password) {
        /*this.inputFirstName.sendKeys(firstName);
        this.inputLastName.sendKeys(lastName);
        this.inputUserName.sendKeys(username);
        this.inputPassword.sendKeys(password);
        this.submitButton.click();*/
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + firstName + "';", inputFirstName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + lastName + "';", inputLastName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", inputUserName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", inputPassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", submitButton);
    }
}
