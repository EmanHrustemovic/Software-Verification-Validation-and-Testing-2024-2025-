import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DeliveryInfoTest {

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
    public void openDeliveryPage()throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        String currentUrl = webDriver.getCurrentUrl();
        System.out.println("We are currently at : " + currentUrl);
        webDriver.navigate().to("https://www.bigbang.ba/nacini-dostave");
        System.out.println("We are redirected successfully to the login page : 'https://www.bigbang.ba/nacini-dostave'");
        Thread.sleep(6000);
    }

    @Test
    @Order(2)
    public void paymentInfo() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/nacini-dostave");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement wayOfPayment = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div/div[2]/div[1]/div/ul/li[1]/a"));
        wayOfPayment.click();
        Thread.sleep(3000);

        // Provjeriti trenutni URL
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/nacini-placanja"; // Pretpostavljen URL stranice
        assertEquals(expectedUrl, currentUrl, "The payment info page did not load!");
        System.out.println("You have access to all info regarding payment on this page.");
    }

    @Test
    @Order(3)
    public void deliveryInfo() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/nacini-dostave");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement wayOfDelivery = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div/div[2]/div[1]/div/ul/li[2]/a"));
        wayOfDelivery.click();
        Thread.sleep(3000);

        // Provjeriti trenutni URL
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/nacini-dostave"; // Pretpostavljen URL stranice
        assertEquals(expectedUrl, currentUrl, "The delivery info page did not load!");
        System.out.println("You have access to all info regarding delivery on this page.");
    }

    @Test
    @Order(4)
    public void termsOfUseInfo() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/nacini-dostave");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement wayOfUse = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div/div[2]/div[1]/div/ul/li[3]/a"));
        wayOfUse.click();
        Thread.sleep(3000);

        // Provjeriti trenutni URL
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/uvjeti-koristenja"; // Pretpostavljen URL stranice
        assertEquals(expectedUrl, currentUrl, "The terms of use page did not load!");
        System.out.println("You have access to all info regarding terms of use on this page.");
    }

    @Test
    @Order(5)
    public void dataPrivacy() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/nacini-dostave");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement privacy = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div/div[2]/div[1]/div/ul/li[4]/a"));
        privacy.click();
        Thread.sleep(3000);

        // Provjeriti trenutni URL
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/privatnost-podataka"; // Pretpostavljen URL stranice
        assertEquals(expectedUrl, currentUrl, "The data privacy page did not load!");
        System.out.println("You have access to all info regarding data privacy on this page.");
    }

    @Test
    @Order(6)
    public void supportInfo() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/nacini-dostave");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement support = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div/div[2]/div[1]/div/ul/li[5]/a"));
        support.click();
        Thread.sleep(3000);

        // Provjeriti trenutni URL
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl = "https://www.bigbang.ba/podrska"; // Pretpostavljen URL stranice
        assertEquals(expectedUrl, currentUrl, "The support page did not load!");
        System.out.println("You have access to all info regarding support on this page.");
    }
}