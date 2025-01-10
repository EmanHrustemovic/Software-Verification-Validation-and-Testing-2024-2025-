import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SearchTests {

    private static WebDriver webDriver;

    @BeforeAll

    public static void setUp(){
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\win11\\OneDrive\\Desktop\\Selenium\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        webDriver = new ChromeDriver(options);
    }

    @Test
    public void testSearchFunctionality() {
        // Set the path to the WebDriver executable (path to chromedriver)


        try {
            // Navigate to Big Bang website
            webDriver.get("https://www.bigbang.ba");

            // Initialize WebDriverWait for dynamic content handling
            WebDriverWait wait = new WebDriverWait(webDriver , Duration.ofSeconds(10));

            // Wait for the search input element to be visible
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.amsearch-input")));

            // Search terms to be typed
            String[] searchTerms = {"iPhone", "PlayStation", "Samsung", "TV", "Kuhalo", "Frižider", "Krema"};

            // Create Actions object to simulate typing
            Actions actions = new Actions(webDriver);

            // Loop through each search term
            for (String term : searchTerms) {
                // Clear the search input field before typing
                searchInput.clear();

                // Type the search term
                actions.sendKeys(searchInput, term).perform();

                // Wait a few seconds (adjust as necessary)
                Thread.sleep(3000);  // 3-second delay between searches
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Close the browser after the test is complete
            webDriver.quit();
        }
    }
}