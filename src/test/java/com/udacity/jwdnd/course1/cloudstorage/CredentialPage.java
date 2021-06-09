package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CredentialPage {


    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(xpath = "//*[@id=\"nav-credentials\"]/button")
    private WebElement addCredentialButton;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUserName;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    @FindBy(xpath = "//*[@id=\"credentialModal\"]/div/div/div[3]/button[2]")
    private WebElement saveCredential;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/th")
    private WebElement credentialTableUrl;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[2]")
    private WebElement credentialTableUserName;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[3]")
    private WebElement credentialTableUserPassword;

    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[1]/button")
    private WebElement editCredential;


    @FindBy(xpath = "//*[@id=\"credentialTable\"]/tbody/tr/td[1]/a")
    private WebElement deleteCredential;

    @FindBy(id = "credentialTable")
    private WebElement credentialTable;

    private final WebDriver driver;

    public CredentialPage(WebDriver webDriver) {
        this.driver=webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void addCredential(String url, String username, String password) {
        //this.credentialsTab.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.credentialsTab);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.addCredentialButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.addCredentialButton);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.credentialUrl.sendKeys(url);
        //this.credentialUserName.sendKeys(username);
        //this.credentialPassword.sendKeys(password);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + url + "';", this.credentialUrl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + username + "';", this.credentialUserName);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + password + "';", this.credentialPassword);
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //this.saveCredential.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.saveCredential);
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //  this.notesTab.click();
    }

    public void clickCredentialsTab() {
        //this.credentialsTab.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.credentialsTab);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTablePassword() {
        //return this.credentialTableUserPassword.getText();
        return this.credentialTableUserPassword.getAttribute("innerHTML");
    }


    public void editNote(String updatedPassword) {
        System.out.println("Clicking Edit Button");
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //this.editCredential.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", this.editCredential);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.credentialPassword.sendKeys(updatedPassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + updatedPassword + "';", this.credentialPassword);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        this.saveCredential.click();
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void deleteNote() {
        this.deleteCredential.click();
    }

  /*  public int getNoteTableSize(){
        List<WebElement> allRows = cred.findElements(By.xpath("/tbody/tr"));
        return allRows.size();
    }
*/

    public String getCredentialTableUrl() {
        //return credentialTableUrl.getText();
        return this.credentialTableUrl.getAttribute("innerHTML");
    }

    public String getCredentialTableUserName() {
        //return credentialTableUserName.getText();
        return this.credentialTableUserName.getAttribute("innerHTML");
    }

    public String getCredentialTableUserPassword() {
        //return credentialTableUserPassword.getText();
        return this.credentialTableUserPassword.getAttribute("innerHTML");
    }

    public void editCredential1() {
        this.editCredential.click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void editCredential2(String editedpassword) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.credentialPassword.sendKeys(editedpassword);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + editedpassword + "';", this.credentialPassword);
        //this.saveCredential.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveCredential);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getCredentialPassword() {
        return this.credentialPassword.getAttribute("value");
    }

    public void clickDeleteCredential() {
        //this.deleteCredential.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteCredential);
    }

    public int getCredentialTableSize() {
        List<WebElement> allRows = credentialTable.findElements(By.xpath("/tbody/tr"));
        return allRows.size();
    }
}
