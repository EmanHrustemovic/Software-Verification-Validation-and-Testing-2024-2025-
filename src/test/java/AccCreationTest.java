/*
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccCreationTest {

    private static WebDriver webDriver;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
    }

    @Test
    @Order(1)
    public void testUserRegistration() {
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        waitForElement(By.linkText("Kreiraj račun"), 10).click();

        fillInput(By.id("firstname"), "Test Name");
        fillInput(By.id("lastname"), "Test Surname");
        fillInput(By.id("email_address"), "testemail@test.com");
        fillInput(By.id("password"), "TestPassword123");
        fillInput(By.id("password-confirmation"), "TestPassword123");

        System.out.println("Please solve the CAPTCHA manually...");
        waitFor(10);

        waitForElement(By.xpath("//button[@title='Kreiraj račun']"), 10).click();
    }

    @Test
    @Order(2)
    public void testLogin() {
        webDriver.get("https://www.bigbang.ba/customer/account/login/");

        fillInput(By.id("email"), "testemail@test.com");
        fillInput(By.id("pass"), "TestPassword123");

        waitForElement(By.id("send2"), 10).click();

        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("customer/account"), "Expected URL to contain 'customer/account', but got: " + currentUrl);
    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    private WebElement waitForElement(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(seconds));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private void fillInput(By locator, String value) {
        WebElement element = webDriver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    private void waitFor(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
*/

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.fail;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AccCreationTest {

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

    @BeforeEach
    public void delayBetweenTests() {
        pause(5000);
    }

    @Test
    @Order(1)
    public void testValidFormSubmission() {
        navigateToForm();
        fillForm("Arnela", "Sokolić", "arnela.test@example.com", "password123", "password123");
        handleCaptcha();
        submitForm();

        if (!isRegistrationComplete()) {
            fail("Test failed: Registration was not completed.");
        }
    }

    @Test
    @Order(2)
    public void testMissingFirstName() {
        navigateToForm();
        fillForm("", "Sokolić", "arnela.test@example.com", "password123", "password123");
        handleCaptcha();
        submitForm();
        checkFailedSubmission("First name is required.");
    }

    @Test
    @Order(3)
    public void testMissingLastName() {
        navigateToForm();
        fillForm("Arnela", "", "arnela.test@example.com", "password123", "password123");
        handleCaptcha();
        submitForm();
        checkFailedSubmission("Last name is required.");
    }

    @Test
    @Order(4)
    public void testInvalidEmail() {
        navigateToForm();
        fillForm("Arnela", "Sokolić", "invalid-email", "password123", "password123");
        handleCaptcha();
        submitForm();
        checkFailedSubmission("Invalid email format.");
    }

    @Test
    @Order(5)
    public void testMissingPassword() {
        navigateToForm();
        fillForm("Arnela", "Sokolić", "arnela.test@example.com", "", "");
        handleCaptcha();
        submitForm();
        checkFailedSubmission("Password is required.");
    }

    @Test
    @Order(6)
    public void testMismatchedPasswords() {
        navigateToForm();
        fillForm("Arnela", "Sokolić", "arnela.test@example.com", "password123", "password456");
        handleCaptcha();
        submitForm();
        checkFailedSubmission("Passwords do not match.");
    }

    @Test
    @Order(7)
    public void testEmptyFields() {
        navigateToForm();
        fillForm("", "", "", "", "");
        handleCaptcha();
        submitForm();
        checkFailedSubmission("All fields are required.");
    }

    private void navigateToForm() {
        webDriver.get("https://www.bigbang.ba/customer/account/create/");
        pause(200);
    }

    private void fillForm(String firstName, String lastName, String email, String password, String confirmPassword) {
        setInput(By.id("firstname"), firstName);
        setInput(By.id("lastname"), lastName);
        setInput(By.id("email_address"), email);
        setInput(By.id("password"), password);
        setInput(By.id("password-confirmation"), confirmPassword);
        pause(100);
    }

    private void setInput(By locator, String value) {
        WebElement element = webDriver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    private void handleCaptcha() {
        System.out.println("CAPTCHA detected. Please complete it manually to continue.");
        pause(5000);
    }

    private void submitForm() {
        webDriver.findElement(By.xpath("//span[text()='Kreiraj račun']")).click();
        pause(2000);
    }

    private boolean isRegistrationComplete() {
        return webDriver.getCurrentUrl().equals("https://www.bigbang.ba/customer/account/");
    }

    private void checkFailedSubmission(String expectedError) {
        if (isRegistrationComplete()) {
            fail("Test failed: Registration should not have been completed.");
        } else {
            System.out.println("Form submission failed as expected: " + expectedError);
        }
    }

    private void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}