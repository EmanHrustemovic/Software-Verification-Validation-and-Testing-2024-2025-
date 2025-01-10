import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PromotionsTest {
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
    public void navigateToPromotions() {
        webDriver.get("https://www.bigbang.ba/");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement promotionsLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a#ui-id-544")));
        promotionsLink.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("https://www.bigbang.ba/promotions"), "Failed to navigate to promotions page.");
    }

    @Test
    @Order(2)
    public void filterGamingPromotions() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement gamingLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='https://www.bigbang.ba/promotions?cat=879']")));
        gamingLink.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("cat=879"), "Failed to filter gaming promotions.");
    }

    @Test
    @Order(3)
    public void navigateToTake2BlackFriday() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement take2Link = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.post-item-link[href='https://www.bigbang.ba/promotions/t2_bf2024']")));
        take2Link.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("t2_bf2024"), "Failed to navigate to Take2 Black Friday promotion.");
    }

    @Test
    @Order(4)
    public void viewAllTake2Products() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement viewAllLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.block-link[href*='product_list_order=bestsellers']")));
        viewAllLink.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("product_list_order=bestsellers"), "Failed to view all Take2 products.");
    }

    @Test
    @Order(5)
    public void selectLegoDrivePS4() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement legoDriveLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("img[alt='Lego 2K Drive PS4']")));
        legoDriveLink.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("lego-2k-drive-ps4"), "Failed to navigate to Lego Drive PS4 product page.");
    }

    @Test
    @Order(6)
    public void addToCart() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button#product-addtocart-button")));
        addToCartButton.click();
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.action.showcart")));
        cartLink.click();
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("checkout/cart"), "Failed to navigate to the cart.");
    }

    @Test
    @Order(7)
    public void testVideoPlay() throws InterruptedException {
        // Navigate to the LEGO 2K Drive product page
        webDriver.get("https://www.bigbang.ba/lego-2k-drive-ps4.html");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        WebElement iframe = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div.embed-container iframe")));
        webDriver.switchTo().frame(iframe);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("document.querySelector('video').play();");

        // Wait for 20 seconds to let the video play
        Thread.sleep(20000);

        // Pause the video
        js.executeScript("document.querySelector('video').pause();");

        webDriver.switchTo().defaultContent();

        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://www.bigbang.ba/lego-2k-drive-ps4.html", currentUrl, "Failed to return to the product page.");
    }

    @Test
    @Order(8)
    public void returnToHomepage() {
        webDriver.get("https://www.bigbang.ba/");
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.equals("https://www.bigbang.ba/"), "Failed to return to the homepage.");
    }
}