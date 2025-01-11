import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GuestCheckoutTest {
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


    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    @Order(1)
    public void addProductToCartAsGuest() {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();

        WebElement tvsEl = webDriver.findElement(By.linkText("TELEVIZORI"));
        tvsEl.click();

        WebElement product = webDriver.findElement(By.className("product-item-photo"));
        product.click();

        WebElement addProduct = webDriver.findElement(By.id("product-addtocart-button"));
        addProduct.click();


        WebElement cart = webDriver.findElement(By.className("minicart-wrapper"));
        cart.click();


        List<WebElement> list = webDriver.findElements(By.xpath("//*[contains(text(),'" + "Artikl" + "')]"));
        assertFalse(list.isEmpty(), "Text not found!");

    }

    @Test
    @Order(2)
    public void viewCartAsGuest() throws InterruptedException {
        webDriver.get(baseUrl);
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement cartIcon = webDriver.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/header/div/div[2]/div[2]/a"));
        cartIcon.click();
        Thread.sleep(3000);

        WebElement cartTitle = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[1]/h1/span"));
        String titleText = cartTitle.getText();

        assertEquals("Košarica", titleText, "Naslov stranice nije 'Košarica'.");
    }



    @Test
    @Order(3)
    public void startCheckoutAsGuest() throws InterruptedException {
        viewCartAsGuest();
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement continueToCheckoutButton = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[3]/div[1]/ul/li/button"));
        continueToCheckoutButton.click();
        Thread.sleep(6000);

        boolean isFormPresent = webDriver.findElements(By.xpath("//*[@id=\"shipping\"]")).size() > 0;

        String message = "Za neregistrovane korisnike postoji forma koju moraju popuniti prije kupovine";

        assertTrue(isFormPresent, "Forma nije pronađena!");

        if (isFormPresent) {
            System.out.println(message);
        }
    }
}