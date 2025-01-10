import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LogoutTest {

    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll

    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.bigbang.ba/";
    }

    @AfterAll

    public static void tearDown(){
        if (webDriver!=null){
            webDriver.quit();
        }
    }

    @Test
    @Order(1)
    public void successfullyLogin() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.sendKeys("ansok.2113@gmail.com");

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.sendKeys("arnelanizama");

        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("send2")));
        loginButton.click();

        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/customer/account/";
        assertEquals(expectedUrl, currentUrl, "Failed to log in.");
    }

    @Test
    @Order(2)
    public void successfullyLogout() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        WebElement promijeniButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.action.switch[data-action='customer-menu-toggle']")));
        promijeniButton.click();

        WebElement odjaviSeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='https://www.bigbang.ba/customer/account/logout/']")));
        odjaviSeButton.click();

        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/";
        assertEquals(expectedUrl, currentUrl, "Failed to log out.");
    }
}