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

    public CredentialPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addCredential(String url, String username, String password) {
        this.credentialsTab.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.addCredentialButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.credentialUrl.sendKeys(url);
        this.credentialUserName.sendKeys(username);
        this.credentialPassword.sendKeys(password);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.saveCredential.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //  this.notesTab.click();
    }

    public void clickCredentialsTab() {
        this.credentialsTab.click();
    }

    public String getTablePassword() {
        return this.credentialTableUserPassword.getText();
    }


    public void editNote(String updatedPassword) {
        System.out.println("Clicking Edit Button");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.editCredential.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.credentialPassword.sendKeys(updatedPassword);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.saveCredential.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
        return credentialTableUrl.getText();
    }

    public String getCredentialTableUserName() {
        return credentialTableUserName.getText();
    }

    public String getCredentialTableUserPassword() {
        return credentialTableUserPassword.getText();
    }

    public void editCredential1() {
        this.editCredential.click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void editCredential2(String editedpassword) {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.credentialPassword.sendKeys(editedpassword);
        this.saveCredential.click();
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
        this.deleteCredential.click();
    }

    public int getCredentialTableSize() {
        List<WebElement> allRows = credentialTable.findElements(By.xpath("/tbody/tr"));
        return allRows.size();
    }
}
