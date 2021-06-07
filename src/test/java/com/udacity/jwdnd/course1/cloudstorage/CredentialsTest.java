package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CredentialsTest {

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
    public void addCredentialsTest() throws InterruptedException {
        String firstName = "Amol";
        String lastname = "Jadhav";
        String username = "amol";
        String password = "amol1234";
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastname, username, password);
        Assertions.assertEquals("Login", driver.getTitle());
        Thread.sleep(3000);
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + this.port + "/login");
        Thread.sleep(3000);
        Assertions.assertEquals("Login", driver.getTitle());
        Thread.sleep(3000);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");
        Thread.sleep(3000);
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.addCredential("www.google.com", "amol", "amol1234");
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(3000);
        credentialPage.clickCredentialsTab();
        Thread.sleep(3000);
        Assertions.assertEquals(credentialPage.getCredentialTableUrl(), "www.google.com");
        Assertions.assertEquals(credentialPage.getCredentialTableUserName(), "amol");
        Assertions.assertNotEquals(credentialPage.getCredentialTableUserPassword(), "amol1234");
    }

    @Test
    public void editCredentialsTest() throws InterruptedException {
        String firstName = "Amol";
        String lastname = "Jadhav";
        String username = "amol1";
        String password = "amol1234";
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastname, username, password);
        Assertions.assertEquals("Login", driver.getTitle());
        Thread.sleep(3000);
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + this.port + "/login");
        Thread.sleep(3000);
        Assertions.assertEquals("Login", driver.getTitle());
        Thread.sleep(3000);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");
        Thread.sleep(3000);
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.addCredential("www.google.com", "amol", "amol1234");
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(3000);
        credentialPage.clickCredentialsTab();
        Thread.sleep(3000);
        Assertions.assertEquals(credentialPage.getCredentialTableUrl(), "www.google.com");
        Assertions.assertEquals(credentialPage.getCredentialTableUserName(), "amol");
        Assertions.assertNotEquals(credentialPage.getCredentialTableUserPassword(), "amol1234");
        credentialPage.editCredential1();
        Thread.sleep(3000);
        Assertions.assertEquals(credentialPage.getCredentialPassword(), "amol1234");
        credentialPage.editCredential2("56");
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(3000);
        credentialPage.clickCredentialsTab();
        Thread.sleep(3000);
        Assertions.assertEquals(credentialPage.getCredentialTableUrl(), "www.google.com");
        Assertions.assertEquals(credentialPage.getCredentialTableUserName(), "amol");
        Assertions.assertNotEquals(credentialPage.getCredentialTableUserPassword(), "amol123456");
    }

    @Test
    public void deleteCredentialsTest() throws InterruptedException {
        String firstName = "Amol";
        String lastname = "Jadhav";
        String username = "amol2";
        String password = "amol1234";
        driver.get("http://localhost:" + this.port + "/signup");
        SignupPage signupPage = new SignupPage(driver);
        signupPage.signup(firstName, lastname, username, password);
        Assertions.assertEquals("Login", driver.getTitle());
        Thread.sleep(3000);
        LoginPage loginPage = new LoginPage(driver);
        driver.get("http://localhost:" + this.port + "/login");
        Thread.sleep(3000);
        Assertions.assertEquals("Login", driver.getTitle());
        Thread.sleep(3000);
        loginPage.login(username, password);
        Assertions.assertEquals("Home", driver.getTitle());
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");
        Thread.sleep(3000);
        CredentialPage credentialPage = new CredentialPage(driver);
        credentialPage.addCredential("www.google.com", "amol", "amol1234");
        Thread.sleep(3000);
        driver.get("http://localhost:" + this.port + "/home");

        Thread.sleep(3000);
        credentialPage.clickCredentialsTab();
        Thread.sleep(3000);
        Assertions.assertEquals(credentialPage.getCredentialTableUrl(), "www.google.com");
        Assertions.assertEquals(credentialPage.getCredentialTableUserName(), "amol");
        Assertions.assertNotEquals(credentialPage.getCredentialTableUserPassword(), "amol1234");
        credentialPage.clickDeleteCredential();
        Thread.sleep(2000);
        driver.get("http://localhost:" + this.port + "/home");
        Thread.sleep(1000);
        credentialPage.clickCredentialsTab();
        Thread.sleep(3000);
        Assertions.assertEquals(credentialPage.getCredentialTableSize(), 0);
    }

}


//*[@id="userTable"]/tbody/tr/th
//*[@id="userTable"]/tbody/tr/td[2]