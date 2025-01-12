import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ResponsiveDesignTest {
    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        // mobile emulation setup for mobile devices
        ChromeOptions options = new ChromeOptions();
        Map<String, Object> mobileEmulation = new HashMap<>();
        mobileEmulation.put("deviceName", "iPhone X");
        options.setExperimentalOption("mobileEmulation", mobileEmulation);
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        driver = new ChromeDriver(options);
    }

    @Test
    @Order(1)
    public void testResponsiveDesignMobile() {
        driver.get("https://www.bigbang.ba/");
    }

    @Test
    @Order(2)
    public void testResponsiveDesignTablet() {
        ChromeOptions tabletOptions = new ChromeOptions();
        Map<String, Object> tabletEmulation = new HashMap<>();
        tabletEmulation.put("deviceName", "iPad");
        tabletOptions.setExperimentalOption("mobileEmulation", tabletEmulation);
        driver.quit();
        driver = new ChromeDriver(tabletOptions);
        driver.get("https://www.bigbang.ba/");
    }

    @Test
    @Order(3)
    public void testLoginOnMobile() throws InterruptedException {
        // Login Test for Mobile Emulation (iPhone X)
        driver.get("https://www.bigbang.ba/customer/account/login/");

        // Wait until the email field and populate it
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        assertTrue(emailField.isDisplayed(), "Email field is not displayed");
        emailField.sendKeys("ansok.2113@gmail.com");

        // Wait for the password field and populate it
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        assertTrue(passwordField.isDisplayed(), "Password field is not displayed");
        passwordField.sendKeys("arnelanizama");

        // Click the "Prijavi se" button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("send2")));
        loginButton.click();

        // Verifying login success
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/customer/account/";
        assertEquals(expectedUrl, currentUrl, "Login failed or the page did not redirect correctly.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}