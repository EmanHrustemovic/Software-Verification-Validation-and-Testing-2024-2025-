import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertFalse;
import static org.testng.AssertJUnit.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddToCartTest {

    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() {
        // Set up WebDriver and the base URL
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.bigbang.ba/"; // Initialize the base URL
    }

    @Test
    @Order(1)
    public void testAddToCart() {
        // Navigate to the "Brandovi" page
        webDriver.get(baseUrl + "/brands");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        // Step 1: Click on the Samsung brand image
        WebElement samsungBrandImage = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img.ambrands-image")));
        samsungBrandImage.click();

        // Step 2: Navigate to the Samsung Refrigerator product page
        webDriver.get("https://www.bigbang.ba/tv-55-samsung-55du7172.html");

        // Step 3: Add the Refrigerator to the cart
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        addToCartButton.click();

        // Wait for the cart to update after adding the first product
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.counter-number")));

        // Step 4: Navigate to another product (Samsung Charger) page
        webDriver.get("https://www.bigbang.ba/pecnica-ugradbena-samsung-nv68a1110bb-ol.html");

        // Step 5: Add the Samsung Charger to the cart
        WebElement addToCartCharger = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        addToCartCharger.click();

        // Wait for the cart to update after adding the second product
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.counter-number")));
    }
    @Test
    @Order(2)
    public void testPriceDisplay() {
        // Navigate to the page where the total price is displayed
        webDriver.get("https://www.bigbang.ba/checkout/cart/");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        // Wait for the price element to be visible
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.amount span.price")));
        // Get the price text and clean it up
        String price = priceElement.getText().trim().replaceAll("\\s+", ""); // Uklanja višak razmaka
        System.out.println("Price: " + price);

    // Check if the price is displayed correctly
    assertFalse(price.isEmpty(), "The price is not displayed.");
    assertEquals("1.538,90KM", price, "The displayed price is incorrect.");
}





    @AfterAll
    public static void tearDown() {
        // Close the WebDriver after all tests
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}