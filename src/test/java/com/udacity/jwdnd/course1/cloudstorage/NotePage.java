package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class NotePage {



    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "add-new-note")
    private WebElement addNewNote;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

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

    public NotePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void addNote(String noteTitle, String noteDescription)   {
        this.notesTab.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.addNewNote.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.noteTitle.sendKeys(noteTitle);
        this.noteDescription.sendKeys(noteDescription);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.noteSubmitButton.click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
      //  this.notesTab.click();
    }

    public void clickNotesTab(){
        this.notesTab.click();
    }

    public String getTitle(){
        return this.noteTitleDisplay.getText();
    }

    public String getNoteDesc(){
        return this.noteDescDisplay.getText();
    }

    public void editNote(String updatedNoteDesc){
        System.out.println("Clicking Edit Button");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.editNoteButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.noteDescription.sendKeys(updatedNoteDesc);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.noteSubmitButton.click();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void deleteNote(){
        this.deleteNoteButton.click();
    }

    public int getNoteTableSize(){
        List<WebElement> allRows = noteTable.findElements(By.xpath("/tbody/tr"));
        return allRows.size();
    }



}
