package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class NotePage {


    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "add-new-note")
    private WebElement addNewNote;

    @FindBy(id = "note-title")
    private WebElement noteTitleEl;

    @FindBy(id = "note-description")
    private WebElement noteDescriptionEl;

    @FindBy(id = "noteSubmit2")
    private WebElement noteSubmitButton;

    @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/th")
    private WebElement noteTitleDisplay;

    @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[2]")
    private WebElement noteDescDisplay;

    @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[1]/button")
    private WebElement editNoteButton;

    @FindBy(xpath = "//*[@id=\"userTable\"]/tbody/tr/td[1]/a")
    private WebElement deleteNoteButton;

    @FindBy(id = "userTable")
    private WebElement noteTable;

    private final WebDriver driver;

    public NotePage(WebDriver webDriver) {
        this.driver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public void addNote(String noteTitle, String noteDescription) {
        //this.notesTab.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", notesTab);
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //this.addNewNote.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewNote);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //this.noteTitle.sendKeys(noteTitle);
        // this.noteDescription.sendKeys(noteDescription);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + noteTitle + "';", this.noteTitleEl);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + noteDescription + "';", this.noteDescriptionEl);
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //this.noteSubmitButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteSubmitButton);
        /*try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //  this.notesTab.click();
    }

    public void clickNotesTab() {
        //this.notesTab.click();

        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", notesTab);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getTitle() {
        //return this.noteTitleDisplay.getAttribute("innerHTML");
        return this.noteTitleDisplay.getText();
    }

    public String getNoteDesc() {
        //return this.noteDescDisplay.getAttribute("innerHTML");
        return this.noteDescDisplay.getText();
    }

    public void editNote(String updatedNoteDesc) {
        System.out.println("Clicking Edit Button");
       /* try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //this.editNoteButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", editNoteButton);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //this.noteDescription.sendKeys(updatedNoteDesc);
        ((JavascriptExecutor) driver).executeScript("arguments[0].value='" + updatedNoteDesc + "';", noteDescriptionEl);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
        //this.noteSubmitButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", noteSubmitButton);
        /*try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    public void deleteNote() {
        //this.deleteNoteButton.click();
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteNoteButton);
    }

    public int getNoteTableSize() {
        List<WebElement> allRows = noteTable.findElements(By.xpath("/tbody/tr"));
        return allRows.size();
    }


}
