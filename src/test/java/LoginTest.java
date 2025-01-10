import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class LoginTest {

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

    public void  testingTitleMatch() throws InterruptedException{
        webDriver.get(baseUrl);
        String ourTitle = webDriver.getTitle();
        System.out.println("Actual title of our webpage is : " +ourTitle);
        assertTrue(ourTitle.contains("Big Bang"),"This title match actual title");
        Thread.sleep(2000);
    }

    @Test
    @Order(2)

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
    @Order(90)

    public void successfullyLogin()throws InterruptedException{
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
    @Order(3)

    public void invalidEmailLogin() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("emanhrutemovic6@gmail.com");
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
            System.out.println("Failed to log in, invalid email !!");
        }
    }

    @Test
    @Order(5)

    public void invalidPassword() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("emanhrutemovic6@gmail.com");
        Thread.sleep(3000);

        WebElement password = webDriver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("emaneman");
        password.submit();
        Thread.sleep(3000);

        String message = "We have successfully logged into our account";
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl ="https://www.bigbang.ba/customer/account/";

        if (currentUrl.equals(expectedUrl)) {
            System.out.println(currentUrl.concat(" - ").concat(expectedUrl));
            System.out.println("We have successfully logged into our account");
        } else {
            System.out.println(currentUrl.concat(" - ").concat(expectedUrl));
            System.out.println("Failed to log in, invalid password !!");
        }
    }

    @Test
    @Order(6)

    public void emptyFields()throws InterruptedException{

        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("");
        Thread.sleep(3000);

        WebElement password = webDriver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("");
        password.submit();
        Thread.sleep(3000);

        String message = "We have successfully logged into our account";
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl ="https://www.bigbang.ba/customer/account/";

        if (currentUrl.equals(expectedUrl)) {
            System.out.println("We have successfully logged into our account");
        } else {
            System.out.println("Failed to log in . Reason : User didn't enter anything !");
        }
    }

    @Test
    @Order(7)

    public void goodEmailButEmptyPassword()throws InterruptedException{

        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("emanhrutemovic6@gmail.com");
        Thread.sleep(3000);

        WebElement password = webDriver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("");
        password.submit();
        Thread.sleep(3000);

        String message = "We have successfully logged into our account";
        String currentUrl = webDriver.getCurrentUrl();
        String expectedUrl ="https://www.bigbang.ba/customer/account/";

        if (currentUrl.equals(expectedUrl)) {
            System.out.println("We have successfully logged into our account");
        } else {
            System.out.println("Failed to log in : Empty password field !!");
        }
    }

    @Test
    @Order(8)

    public void emptyEmailButGoodPassword()throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("");
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
            System.out.println("Failed to log in : Empty email field !!");
        }
    }

    @Test
    @Order(9)

    public void testingRememberMeMethod() throws InterruptedException{

        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement email = webDriver.findElement(By.cssSelector("input[type='email']"));
        email.sendKeys("emanhrustemovic6@gmail.com");
        Thread.sleep(3000);

        WebElement password = webDriver.findElement(By.cssSelector("input[type='password']"));
        password.sendKeys("g3c9h.,1?0");
        password.submit();
        Thread.sleep(3000);

        WebElement rememberMe = webDriver.findElement(By.cssSelector("input[type='checkbox']"));
        rememberMe.submit();

        if (!rememberMe.isSelected()) {
            rememberMe.click();
        }
    }

}