package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NoteTest {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }


    @Test
    public void addNoteTest() throws InterruptedException {
        String firstName = "Amol";
        String lastname = "Jadhav";
        String username = "amol";
        String password = "amol1234";
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastname, username, password);
        Assertions.assertEquals("Sign Up", driver.getTitle());
        Thread.sleep(1000);
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());
        Thread.sleep(1000);
        driver.get("http://localhost:" + this.port + "/home");
        NotePage notePage = new NotePage(driver);
        notePage.addNote("First Note", "This if my first Note");
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(5000);
        notePage.clickNotesTab();
        Thread.sleep(5000);
        System.out.println("notePage.getTitle():-" + notePage.getTitle());
        System.out.println("notePage.getNoteDesc():-" + notePage.getNoteDesc());
        Assertions.assertEquals("First Note", notePage.getTitle());
        Assertions.assertEquals("This if my first Note", notePage.getNoteDesc());
    }

    @Test
    public void editNoteTest() throws InterruptedException {
        String firstName = "Amol";
        String lastname = "Jadhav";
        String username = "amol5";
        String password = "amol12345";
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastname, username, password);
        Assertions.assertEquals("Sign Up", driver.getTitle());
        Thread.sleep(1000);
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());
        Thread.sleep(1000);
        driver.get("http://localhost:" + this.port + "/home");
        NotePage notePage = new NotePage(driver);
        notePage.addNote("First Note", "This if my first Note");
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(3000);

        notePage.clickNotesTab();
        Thread.sleep(3000);
        Assertions.assertEquals("First Note", notePage.getTitle());
        Assertions.assertEquals("This if my first Note", notePage.getNoteDesc());
        System.out.println("notePage.getTitle() 2 :-" + notePage.getTitle());
        System.out.println("notePage.getNoteDesc() 2 :-" + notePage.getNoteDesc());
        Thread.sleep(5000);
        notePage.editNote(" updated");
        driver.get("http://localhost:" + this.port + "/home");
        Thread.sleep(5000);
        notePage.clickNotesTab();
        Thread.sleep(5000);
        System.out.println("notePage.getTitle():-" + notePage.getTitle());
        System.out.println("notePage.getNoteDesc():-" + notePage.getNoteDesc());
        Assertions.assertEquals("First Note", notePage.getTitle());
        Assertions.assertEquals("This if my first Note updated", notePage.getNoteDesc());
    }

    @Test
    public void deleteNoteTest() throws InterruptedException {
        String firstName = "Amol";
        String lastname = "Jadhav";
        String username = "amol56";
        String password = "amol123456";
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastname, username, password);
        Assertions.assertEquals("Sign Up", driver.getTitle());
        Thread.sleep(1000);
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());
        Thread.sleep(1000);
        driver.get("http://localhost:" + this.port + "/home");
        NotePage notePage = new NotePage(driver);
        notePage.addNote("First Note", "This if my first Note");
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(3000);

        notePage.clickNotesTab();
        Thread.sleep(3000);
        Assertions.assertEquals("First Note", notePage.getTitle());
        Assertions.assertEquals("This if my first Note", notePage.getNoteDesc());
        System.out.println("notePage.getTitle() 2 :-" + notePage.getTitle());
        System.out.println("notePage.getNoteDesc() 2 :-" + notePage.getNoteDesc());
        Thread.sleep(5000);
        notePage.deleteNote();
        Thread.sleep(5000);
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(3000);

        notePage.clickNotesTab();
        Thread.sleep(3000);
        Assertions.assertEquals(0, notePage.getNoteTableSize());
    }

}


//*[@id="userTable"]/tbody/tr/th
//*[@id="userTable"]/tbody/tr/td[2]