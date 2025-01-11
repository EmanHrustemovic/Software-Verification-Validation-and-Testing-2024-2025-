import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class AddToCartAndPriceCheckTest {

    private WebDriver webDriver;
    private static final String BASE_URL = "https://www.bigbang.ba/";

    @BeforeAll
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
    }

    @Test
    public void testAddToCart() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        webDriver.get(BASE_URL + "/brands");

        WebElement samsungBrandImage = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img.ambrands-image")));
        samsungBrandImage.click();

        webDriver.get("https://www.bigbang.ba/tv-55-samsung-55du7172.html");

        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        addToCartButton.click();

        WebElement cartCounter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.counter-number")));
        assertTrue(Integer.parseInt(cartCounter.getText()) >= 1, "Cart counter did not update after adding the first product.");

        webDriver.get("https://www.bigbang.ba/pecnica-ugradbena-samsung-nv68a1110bb-ol.html");

        WebElement addToCartCharger = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        addToCartCharger.click();

        cartCounter = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("span.counter-number")));
        assertTrue(Integer.parseInt(cartCounter.getText()) >= 2, "Cart counter did not update after adding the second product.");
    }

    @Test
    public void testPriceDisplay() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.get(BASE_URL + "/checkout/cart/");

        //  Wait for the price element to load
        WebElement priceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("td.amount span.price")));

        //  Get the price text
        String actualPrice = priceElement.getText().trim();
        System.out.println("Price: " + actualPrice);

        // Define the price
        String expectedPrice = "1.529,80 KM";
        assertEquals(expectedPrice, actualPrice, "The price is not displayed correctly.");
    }

    @AfterAll
    public void tearDown() {
        // Close the WebDriver after all tests
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}