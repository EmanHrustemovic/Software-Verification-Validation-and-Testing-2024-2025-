import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SessionPersistenceAcrossPagesTest {

    private static WebDriver webDriver;
    private static String baseUrl;
    private String initialSessionId;
    private String sessionIdAfterPromotions;
    private String sessionIdAfterBrands;
    private String sessionIdAfterNewsletterSignup;

    @BeforeAll
    public static void setUp() {
        // Set up the ChromeDriver and configure the options
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.bigbang.ba/";
    }

    @AfterAll
    public static void tearDown() {
        // Close the browser after all tests are complete
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @Order(1)
    public void recordSessionIDOnHomePage() {
        // Navigate to the home page
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();

        // Record the initial session ID from the cookie
        initialSessionId = webDriver.manage().getCookieNamed("PHPSESSID").getValue();
        System.out.println("Initial Session ID on home page: " + initialSessionId);

        // Assert that the session ID is not null
        assertNotNull(initialSessionId, "Session ID should not be null on initial page load.");
    }

    @Test
    @Order(2)
    public void validateSessionIDAfterNavigatingToPromotions() {
        // Navigate to the promotions page
        webDriver.get(baseUrl + "promotions");

        // Record the session ID after navigating to the promotions page
        sessionIdAfterPromotions = webDriver.manage().getCookieNamed("PHPSESSID").getValue();
        System.out.println("Session ID after navigating to Promotions page: " + sessionIdAfterPromotions);

        // Verify that the session ID remains consistent across the home page and the promotions page
        assertEquals(initialSessionId, sessionIdAfterPromotions, "Session ID should remain consistent when navigating to Promotions page.");
    }

    @Test
    @Order(3)
    public void validateSessionIDAfterNavigatingToBrands() {
        // Navigate to the brands page
        webDriver.get(baseUrl + "brands");

        // Record the session ID after navigating to the brands page
        sessionIdAfterBrands = webDriver.manage().getCookieNamed("PHPSESSID").getValue();
        System.out.println("Session ID after navigating to Brands page: " + sessionIdAfterBrands);

        // Verify that the session ID remains consistent when navigating to the Brands page
        assertEquals(initialSessionId, sessionIdAfterBrands, "Session ID should remain consistent when navigating to Brands page.");
    }

    @Test
    @Order(4)
    public void validateSessionIDAfterNavigatingToNewsletterSignup() {
        // Navigate to the newsletter signup page
        webDriver.get(baseUrl + "newsletter-prijava");

        // Record the session ID after navigating to the newsletter signup page
        sessionIdAfterNewsletterSignup = webDriver.manage().getCookieNamed("PHPSESSID").getValue();
        System.out.println("Session ID after navigating to Newsletter Signup page: " + sessionIdAfterNewsletterSignup);

        // Verify that the session ID remains consistent when navigating to the Newsletter Signup page
        assertEquals(initialSessionId, sessionIdAfterNewsletterSignup, "Session ID should remain consistent when navigating to Newsletter Signup page.");
    }

    @Test
    @Order(5)
    public void validateSessionIDAcrossAllPages() {
        // Validate that session IDs are consistent across all visited pages
        assertEquals(initialSessionId, sessionIdAfterPromotions, "Session ID mismatch between Home and Promotions page.");
        assertEquals(initialSessionId, sessionIdAfterBrands, "Session ID mismatch between Home and Brands page.");
        assertEquals(initialSessionId, sessionIdAfterNewsletterSignup, "Session ID mismatch between Home and Newsletter Signup page.");
        System.out.println("Session ID remains consistent across all pages.");
    }
}