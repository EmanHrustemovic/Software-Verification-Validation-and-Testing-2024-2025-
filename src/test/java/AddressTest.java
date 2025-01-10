import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

import static org.testng.Assert.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AddressTest {
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

    public void openLogin() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        String currentUrl = webDriver.getCurrentUrl();
        System.out.println("We are currently at : " + currentUrl);
        //assertEquals("https://www.bigbang.ba/customer/account/login/",currentUrl);
        webDriver.navigate().to("https://www.bigbang.ba/customer/account/login/");
        System.out.println("We are redirected successfully to the login page : 'https://www.bigbang.ba/customer/account/login/'");
        Thread.sleep(6000);
    }
    @Test
    @Order(3)

    public void successfullyLogin()throws InterruptedException{
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

        WebElement promijeniButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.action.switch[data-action='customer-menu-toggle']")));
        promijeniButton.click();

        WebElement odjaviSeButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href='https://www.bigbang.ba/customer/account/logout/']")));
        odjaviSeButton.click();

        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("emanhrustemovic6@gmail.com");
        Thread.sleep(3000);

        WebElement password = webDriver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("eman03hrustemovic04");
        password.submit();
        Thread.sleep(3000);

        String message = "We have successfully logged into our account";
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl ="https://www.bigbang.ba/customer/account/";

        if (currentUrl.equals(expectedUrl)) {
            System.out.println("We have successfully logged into our account");
        } else {
            System.out.println("Failed to log in");
        }
    }


    @Test
    @Order(2)
    public void addressForAccount() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        //webDriver.manage().window().maximize();
        Thread.sleep(3000);

        // Log in
        WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("emanhrustemovic6@gmail.com");
        Thread.sleep(3000);

        WebElement password = webDriver.findElement(By.id("pass"));
        password.sendKeys("eman03hrustemovic04");
        password.submit();
        Thread.sleep(2000);

        // Navigate to account page
        webDriver.navigate().to("https://www.bigbang.ba/customer/account/");
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals("https://www.bigbang.ba/customer/account/", currentUrl, "Failed to navigate to the account page!");

        // Click on address section
        WebElement accAddress = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[4]/div[1]/a"));
        accAddress.click();
        Thread.sleep(4000);

        WebElement addNewAddress =webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[2]/div[1]/div[5]/div[1]/button"));
        addNewAddress.click();
        Thread.sleep(4000);

        // Fill out the address form
        WebElement name = webDriver.findElement(By.id("firstname"));
        name.clear();
        name.sendKeys("Arnela");

        WebElement lastname = webDriver.findElement(By.id("lastname"));
        lastname.clear();
        lastname.sendKeys("Sokolić");

        WebElement phone = webDriver.findElement(By.id("telephone")); // Correct XPath for phone
        phone.clear();
        phone.sendKeys("00387225883");
        Thread.sleep(7000);


        WebElement fax = webDriver.findElement(By.id("fax"));
        fax.clear();
        fax.sendKeys("as15d56d89d8");

        WebElement address = webDriver.findElement(By.id("street_1"));
        address.clear();
        address.sendKeys("Francuske revolucije, Ilidža");

        WebElement city = webDriver.findElement(By.id("city"));
        city.clear();
        city.sendKeys("Sarajevo");

        WebElement postalNumber = webDriver.findElement(By.id("zip"));
        postalNumber.clear();
        postalNumber.sendKeys("756962");

        WebElement submitButton = webDriver.findElement(By.xpath("//*[@id=\"form-validate\"]/div/div[1]/button"));
        submitButton.click();

        // Verify success
        Thread.sleep(3000); // Wait for redirection or address update
        String currentUrl1 = webDriver.getCurrentUrl();
        assertEquals("https://www.bigbang.ba/customer/address/index/", currentUrl1, "Failed to navigate to the address page!");

        System.out.println("Address successfully added!");
    }

}