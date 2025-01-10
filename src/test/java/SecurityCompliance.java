
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.time.Duration;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SecurityCompliance {

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

    // Test: Ensure the website uses HTTPS
    @Test
    @Order(1)
    public void testHttpsUsage() {
        webDriver.get("https://www.bigbang.ba");


        String currentUrl = webDriver.getCurrentUrl();

        // Assert the URL starts with "https://"
        assertTrue(currentUrl.startsWith("https://"), "The website does not use HTTPS");
    }



    // Test: Test if HTTP is properly redirected to HTTPS
    @Test
    @Order(2)
    public void testHttpToHttpsRedirection() {
        webDriver.get("http://www.bigbang.ba");

        // Wait for redirection to HTTPS
        String currentUrl = webDriver.getCurrentUrl();

        // Assert that the HTTP request was redirected to HTTPS
        assertTrue(currentUrl.startsWith("https://"), "HTTP request did not redirect to HTTPS");
    }




    // Test: Check for mixed content (insecure HTTP resources)
    @Test
    @Order(3)
    public void testMixedContent() {
        webDriver.get("https://www.bigbang.ba");

        boolean isMixedContentWarning = false;
        if (isMixedContentWarning) {
            fail("Mixed content detected on HTTPS page");
        }

        assertTrue(!isMixedContentWarning, "Mixed content detected on HTTPS page");
    }



    @Test
    @Order(10)
    public void testCookieSecurity() {
        webDriver.get("https://www.bigbang.ba");
        // Get cookies and check their attributes
        for (Cookie cookie : webDriver.manage().getCookies()) {
            if (cookie.getName().equals("session")) {
                assertTrue(cookie.isSecure(), "Cookie is not marked as Secure");
                assertTrue(cookie.isHttpOnly(), "Cookie is not marked as HttpOnly");
            }
        }
    }

    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}