import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserJourney1Test {
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
    public void testAllIconsNavigation() {
        webDriver.get("https://www.bigbang.ba");
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));



        // Veliki kućanski
        WebElement iconLink1 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/bijela-tehnika.html']")));
        iconLink1.click();
        assertEquals("https://www.bigbang.ba/bijela-tehnika.html", webDriver.getCurrentUrl(), "Navigation for Veliki kućanski failed.");
        webDriver.navigate().back();  // Navigate back to the home page

        // Televizori
        WebElement iconLink2 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/televizori/led-tv.html']")));
        iconLink2.click();
        assertEquals("https://www.bigbang.ba/televizori/led-tv.html", webDriver.getCurrentUrl(), "Navigation for Televizori failed.");
        webDriver.navigate().back();

        // Mali kućanski
        WebElement iconLink3 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/mali-kucanski-aparati.html']")));
        iconLink3.click();
        assertEquals("https://www.bigbang.ba/mali-kucanski-aparati.html", webDriver.getCurrentUrl(), "Navigation for Mali kućanski failed.");
        webDriver.navigate().back();

        // Mobiteli
        WebElement iconLink4 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/komunikacije/mobiteli.html']")));
        iconLink4.click();
        assertEquals("https://www.bigbang.ba/komunikacije/mobiteli.html", webDriver.getCurrentUrl(), "Navigation for Mobiteli failed.");
        webDriver.navigate().back();

        // Laptopi
        WebElement iconLink5 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/racunala-i-periferija/prijenosna-racunala.html']")));
        iconLink5.click();
        assertEquals("https://www.bigbang.ba/racunala-i-periferija/prijenosna-racunala.html", webDriver.getCurrentUrl(), "Navigation for Laptopi failed.");
        webDriver.navigate().back();

        // Osobna njega
        WebElement iconLink6 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/osobna-njega.html']")));
        iconLink6.click();
        assertEquals("https://www.bigbang.ba/osobna-njega.html", webDriver.getCurrentUrl(), "Navigation for Osobna njega failed.");
        webDriver.navigate().back();

        // Gaming
        WebElement iconLink7 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/gaming.html']")));
        iconLink7.click();
        assertEquals("https://www.bigbang.ba/gaming.html", webDriver.getCurrentUrl(), "Navigation for Gaming failed.");
        webDriver.navigate().back();

        // Outlet
        WebElement iconLink8 = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/outlet.html']")));
        iconLink8.click();
        assertEquals("https://www.bigbang.ba/outlet.html", webDriver.getCurrentUrl(), "Navigation for Outlet failed.");
        webDriver.navigate().back();
    }

    @Test
    @Order(2)
    public void testGamingAndJoystickNavigation() {
        webDriver.get("https://www.bigbang.ba/gaming.html");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        WebElement gamingPriborImage = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//img[@src='https://cdn.sancta-domenica.hr/media/catalog/category/cat_pribor.png']")));
        gamingPriborImage.click();


        assertEquals("https://www.bigbang.ba/gaming/gaming-pribor.html", webDriver.getCurrentUrl(), "Navigation to Gaming Pribor failed.");

        // Click on the joystick image
        WebElement joystickImage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//img[@src='https://cdn.sancta-domenica.hr/media/catalog/product/cache/ea5264e8466b4f5015cfdd7dff28e7d2/d/u/dualsense_pearl_pr_01_rgb_1.png']")));
        assertTrue(joystickImage.isDisplayed(), "Joystick image is not displayed.");
    }

    @Test
    @Order(3)
    public void testAddToCart() {
        // Navigate to the Gaming section
        webDriver.get("https://www.bigbang.ba/gaming/gaming-pribor.html");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='https://www.bigbang.ba/gaming/gaming-pribor/ps5-dualsense-wireless-controller-chroma-pearl.html']")));
        productLink.click();

        assertEquals("https://www.bigbang.ba/gaming/gaming-pribor/ps5-dualsense-wireless-controller-chroma-pearl.html", webDriver.getCurrentUrl(), "Navigation to the product page failed.");

        // Click the "Add to Cart" button
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("product-addtocart-button")));
        addToCartButton.click();

        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.contains("cart"), "Failed to navigate to the cart after adding the product.");
    }

    @Test
    @Order(4)
    public void successfullyLogin() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        // Wait for the email field and populate it
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='email']")));
        emailField.sendKeys("ansok.2113@gmail.com");

        // Wait for the password field and populate it
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[type='password']")));
        passwordField.sendKeys("arnelanizama");

        // Click the "Prijavi se" button
        WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("send2")));
        loginButton.click();

        // Verifying login success
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/customer/account/";
        assertEquals(expectedUrl, currentUrl, "Failed to log in.");
    }

    @Test
    @Order(5)
    public void testTabsNavigation() {
        webDriver.get("https://www.bigbang.ba/gaming/gaming-pribor/ps5-dualsense-wireless-controller-chroma-pearl.html");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        // Test clicking the Specifications tab
        WebElement specificationsTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-label-additional-title")));
        specificationsTab.click();
        WebElement specificationsSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional")));
        assertTrue(specificationsSection.isDisplayed(), "Specifications section is not displayed after clicking the tab.");

        // Test clicking the Video tab
        WebElement videoTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-label-product.info.description.video-title")));
        videoTab.click();
        WebElement videoSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product.info.description.video")));
        assertTrue(videoSection.isDisplayed(), "Video section is not displayed after clicking the tab.");

        // Test clicking the Download tab
        WebElement downloadTab = wait.until(ExpectedConditions.elementToBeClickable(By.id("tab-label-product.info.product-question-title")));
        downloadTab.click();
        WebElement downloadSection = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("product.info.product-question")));
        assertTrue(downloadSection.isDisplayed(), "Download section is not displayed after clicking the tab.");
    }


    @Test
    @Order(6)
    public void testEditAndSavePersonalInfo() {
        webDriver.get("https://www.bigbang.ba/customer/account/");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        // Click the "Uredi" button
        WebElement editButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Uredi']")));
        editButton.click();


        WebElement firstNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));
        firstNameField.clear();
        firstNameField.sendKeys("ArnelaaSS");


        WebElement lastNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("lastname")));
        lastNameField.clear(); // Clear the existing value
        lastNameField.sendKeys("Sokolić");

        WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit' and @title='Spremi']")));
        saveButton.click();

        WebElement successMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'message-success')]")));
        assertTrue(successMessage.isDisplayed(), "Success message not displayed after saving changes.");
    }

    @Test
    @Order(7)
    public void testReturnButton() {
        // Navigate to the account page or the page where the "Vratite se" button is located
        webDriver.get("https://www.bigbang.ba/customer/account/edit/");

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        // "Vratite se" button
        WebElement returnButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Vratite se']")));
        returnButton.click();
    }

    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}