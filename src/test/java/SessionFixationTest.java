package com.example;

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SessionFixationTest {

    private static WebDriver webDriver;
    private static String baseUrl;
    private String sessionIdBeforeLogin;
    private String sessionIdAfterLogin;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.bigbang.ba/";
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @Order(1)
    public void openLoginPageAndRecordSessionID() {
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();

        // Zabilježiti trenutni Session ID iz kolačića
        sessionIdBeforeLogin = webDriver.manage().getCookieNamed("PHPSESSID").getValue();
        System.out.println("Session ID prije prijave: " + sessionIdBeforeLogin);
    }

    @Test
    @Order(2)
    public void loginAndRecordNewSessionID() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.sendKeys("ansok.2113@gmail.com");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.sendKeys("arnelanizama");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("send2")));
        loginButton.click();

        wait.until(ExpectedConditions.urlToBe("https://www.bigbang.ba/customer/account/"));

        // Zabilježiti novi Session ID nakon prijave
        sessionIdAfterLogin = webDriver.manage().getCookieNamed("PHPSESSID").getValue();
        System.out.println("Session ID nakon prijave: " + sessionIdAfterLogin);
    }

    @Test
    @Order(3)
    public void verifySessionIDChanged() {
        // Provjera da li se Session ID promijenio nakon prijave
        assertNotEquals(sessionIdBeforeLogin, sessionIdAfterLogin, "Session ID se nije promijenio nakon prijave. To može predstavljati sigurnosni rizik.");
    }
}