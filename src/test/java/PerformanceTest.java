import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PerformanceTest {

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
    @Order(1)
    public void testHomepageLoadPerformance() {
        long startTime = System.currentTimeMillis();
        webDriver.get("https://www.bigbang.ba");
        long endTime = System.currentTimeMillis();

        long loadTime = endTime - startTime;
        System.out.println("Homepage Load Time: " + loadTime + " milliseconds");

        assertTrue(loadTime < 5000, "Homepage took too long to load"); // 5 seconds max load time
    }


    @Test
    @Order(2)
    public void testAkcijeAndPromocijePerformance() {
        long startTime = System.currentTimeMillis();
        webDriver.get("https://www.bigbang.ba/promotions");
        long endTime = System.currentTimeMillis();

        long loadTime = endTime - startTime;
        System.out.println("AkcijeAndPromocije Load Time: " + loadTime + " milliseconds");
        assertTrue(loadTime < 5000, "Homepage took too long to load");
    }


    @Test
    @Order(3)
    public void testBrandsPerformance() {
        long startTime = System.currentTimeMillis();
        webDriver.get("https://www.bigbang.ba/brands");
        long endTime = System.currentTimeMillis();

        long loadTime = endTime - startTime;
        System.out.println("Brands Load Time: " + loadTime + " milliseconds");

        assertTrue(loadTime < 5000, "Homepage took too long to load");
    }




    @Test
    @Order(4)
    public void testNewsletterPerformance() {
        long startTime = System.currentTimeMillis();
        webDriver.get("https://www.bigbang.ba/newsletter-prijava");
        long endTime = System.currentTimeMillis();

        long loadTime = endTime - startTime;
        System.out.println("Newsletter Load Time: " + loadTime + " milliseconds");

        assertTrue(loadTime < 5000, "Homepage took too long to load");
    }


    @Test
    @Order(5)
    public void testCartPerfomance() {
        long startTime = System.currentTimeMillis();
        webDriver.get("https://www.bigbang.ba/checkout/cart/");
        long endTime = System.currentTimeMillis();

        long loadTime = endTime - startTime;
        System.out.println("Cart Load Time: " + loadTime + " milliseconds");

        assertTrue(loadTime < 5000, "Homepage took too long to load");
    }


    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}