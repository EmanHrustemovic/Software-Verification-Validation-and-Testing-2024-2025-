/*
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ContactFormTests {

    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.bigbang.ba/";
    }

    @BeforeEach
    void navigateToContactPage() {
        webDriver.get("https://www.bigbang.ba/contact");
    }

    @Test
    @Order(100)
    void testValidFormSubmission() {
        webDriver.findElement(By.id("name")).sendKeys("Test User");
        webDriver.findElement(By.id("email")).sendKeys("test@example.com");
        webDriver.findElement(By.id("message")).sendKeys("Ovo je validna poruka.");
        webDriver.findElement(By.id("submit-button")).click();

        // Provjera poruke uspješnog slanja
        WebElement successMessage = webDriver.findElement(By.id("success-message"));
        assertTrue(successMessage.isDisplayed(), "Poruka uspjeha nije prikazana.");
    }

    @Test
    @Order(3)
    void testEmptyFields() {
        webDriver.findElement(By.id("submit-button")).click();

        // Provjera grešaka
        WebElement errorMessage = webDriver.findElement(By.id("error-message"));
        assertTrue(errorMessage.isDisplayed(), "Poruka greške nije prikazana.");
    }

    @Test
    @Order(1)
    void testInvalidEmail() {
        webDriver.findElement(By.id("name")).sendKeys("Test User");
        webDriver.findElement(By.id("email")).sendKeys("test@");
        webDriver.findElement(By.id("message")).sendKeys("Ovo je validna poruka.");
        webDriver.findElement(By.id("submit-button")).click();

        WebElement emailError =webDriver.findElement(By.id("email-error"));
        assertTrue(emailError.isDisplayed(), "Poruka greške za email nije prikazana.");
    }

    @Test
    @Order(4)
    void testShortMessage() {
        webDriver.findElement(By.id("name")).sendKeys("Test User");
        webDriver.findElement(By.id("email")).sendKeys("test@example.com");
        webDriver.findElement(By.id("message")).sendKeys("Kratko");
        webDriver.findElement(By.id("submit-button")).click();

        WebElement messageError = webDriver.findElement(By.id("message-error"));
        assertTrue(messageError.isDisplayed(), "Poruka greške za kratku poruku nije prikazana.");
    }

    @Test
    @Order(2)
    void testInvalidCharactersInName() {
        webDriver.findElement(By.id("name")).sendKeys("!@#$%");
        webDriver.findElement(By.id("email")).sendKeys("test@example.com");
        webDriver.findElement(By.id("message")).sendKeys("Ovo je validna poruka.");
        webDriver.findElement(By.id("submit-button")).click();

        WebElement nameError = webDriver.findElement(By.id("name-error"));
        assertTrue(nameError.isDisplayed(), "Poruka greške za ime nije prikazana.");
    }

    @AfterAll
    void tearDown() {
        webDriver.quit();
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
public class ContactFormTests {

    private WebDriver webDriver;

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
    }

    @BeforeEach
    public void delayBetweenTests() {
        pause(5000);
    }

    @Test
    @Order(1)
    public void testValidFormSubmission() {
        navigateToForm();
        fillForm("Arnela Sokolić", "s.arnela21@gmail.com", "123456789", "This is a test message.");
        submitForm();

        handleCaptcha();

        if (!isFormSubmittedSuccessfully()) {
            fail("Test failed: Form submission was not successful.");
        }
    }

    @Test
    @Order(2)
    public void testMissingName() {
        navigateToForm();
        fillForm("", "s.arnela21@gmail.com", "123456789", "This is a test message.");
        submitForm();

        handleCaptcha();

        checkFailedSubmission("Name is required.");
    }

    @Test
    @Order(3)
    public void testMissingEmail() {
        navigateToForm();
        fillForm("Arnela Sokolić", "", "123456789", "This is a test message.");
        submitForm();

        handleCaptcha();

        checkFailedSubmission("Email is required.");
    }

    @Test
    @Order(4)
    public void testInvalidEmail() {
        navigateToForm();
        fillForm("Arnela Sokolić", "invalid-email", "123456789", "This is a test message.");
        submitForm();

        handleCaptcha();

        checkFailedSubmission("Invalid email format.");
    }

    @Test
    @Order(5)
    public void testMissingMessage() {
        navigateToForm();
        fillForm("Arnela Sokolić", "s.arnela21@gmail.com", "123456789", "");
        submitForm();

        handleCaptcha();

        checkFailedSubmission("Message is required.");
    }

    @Test
    @Order(6)
    public void testEmptyFields() {
        navigateToForm();
        fillForm("", "", "", "");
        submitForm();

        handleCaptcha();

        checkFailedSubmission("All fields are required.");


    }
    @Test
    @Order(7)
    public void emptyPhoneNumberField() {
        navigateToForm();
        fillForm("Arnela Sokolić", "s.arnela21@gmail.com", "", "This is a test message.");
        submitForm();

        handleCaptcha();

        if (!isFormSubmittedSuccessfully()) {
            fail("Test failed: Form submission should be successful even without a phone number.");
        }
    }


    private void navigateToForm() {
        webDriver.get("https://www.bigbang.ba/contact");
        pause(2000);
    }

    private void fillForm(String name, String email, String phone, String message) {
        setInput(By.id("name"), name);
        setInput(By.id("email"), email);
        setInput(By.id("telephone"), phone);
        setInput(By.id("comment"), message);
        pause(100);
    }

    private void setInput(By locator, String value) {
        WebElement element = webDriver.findElement(locator);
        element.clear();
        element.sendKeys(value);
    }

    private void submitForm() {
        webDriver.findElement(By.xpath("//span[text()='Pošalji']")).click();
        pause(2000);
    }

    private boolean isFormSubmittedSuccessfully() {
        // Checking if the page redirects to the confirmation page
        return webDriver.getCurrentUrl().equals("https://www.bigbang.ba/contact/index/");
    }

    private void checkFailedSubmission(String expectedError) {
        if (isFormSubmittedSuccessfully()) {
            fail("Test failed: Form should not have been submitted.");
        } else {
            System.out.println("Form submission failed as expected: " + expectedError);
        }
    }

    private void handleCaptcha() {
        System.out.println("CAPTCHA detected. Please complete it manually to continue.");
        pause(5000); // Wait for the user to complete the CAPTCHA
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