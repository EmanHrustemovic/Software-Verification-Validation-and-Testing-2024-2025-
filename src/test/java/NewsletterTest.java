import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class NewsletterTest {

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

    @Test
    public void testNewsletterSubscription() {
        // Navigating to the newsletter page
        webDriver.get("https://www.bigbang.ba/newsletter-prijava");

        waitFor(3);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement nameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("ime")));
        nameField.sendKeys("Test");

        WebElement emailField = wait.until(ExpectedConditions.elementToBeClickable(By.name("email")));
        emailField.sendKeys("test@gmail.com");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit' and @value='PRIJAVI SE']")));
        submitButton.click();

        waitFor(3);

        // Verify if the URL after submission matches the expected confirmation page URL
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://email.bigbang.ba/x/plugin/?pName=subscribe&MIDRID=S7Y1BQAA46&pLang=hr&Z=-1304934529";

        if (!currentUrl.equals(expectedUrl)) {
            fail("Test failed. Form submission did not redirect to the confirmation page. Current URL: " + currentUrl);
        }
    }

    @Test
    @Order(3)
    void testMissingName() {
        webDriver.get("https://www.bigbang.ba/newsletter-prijava");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ime")));
        nameField.sendKeys(""); // Empty name

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys("arnela@example.com");

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        submitButton.click();

        // Verify if the page URL hasn't changed and we are still on the same form page
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/newsletter-prijava";

        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Form submission failed: Missing name field.");
        }
    }

    @Test
    @Order(4)
    void testNameOnly() {
        webDriver.get("https://www.bigbang.ba/newsletter-prijava");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ime")));
        nameField.sendKeys("Arnela");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys(""); // Empty email

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        submitButton.click();

        // Verify if the page URL hasn't changed and we are still on the same form page
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/newsletter-prijava";

        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Form submission failed: Missing email field.");
        }
    }

    @Test
    @Order(5)
    void testBothFieldsEmpty() {
        webDriver.get("https://www.bigbang.ba/newsletter-prijava");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ime")));
        nameField.sendKeys(""); // Empty name

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys(""); // Empty email

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        submitButton.click();

        // Verify if the page URL hasn't changed and we are still on the same form page
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/newsletter-prijava";

        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Form submission failed: Missing both name and email.");
        }
    }

    @Test
    @Order(6)
    void testEmailMissing() {
        webDriver.get("https://www.bigbang.ba/newsletter-prijava");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("ime")));
        nameField.sendKeys("Test Name");

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("email")));
        emailField.sendKeys(""); // Empty email

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//input[@type='submit']")));
        submitButton.click();

        // Verify if the page URL hasn't changed and we are still on the same form page
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/newsletter-prijava";

        if (currentUrl.equals(expectedUrl)) {
            System.out.println("Form submission failed: Missing email field.");
        }
    }

    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    private void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}