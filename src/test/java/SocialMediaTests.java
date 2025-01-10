/*

import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocialMediaTests {

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

    public void findPageBottom() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement bottom = webDriver.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/footer/div[2]"));
        bottom.click();
        Thread.sleep(3000);

        String message = "";

        if (bottom.isSelected()){
            message ="On the bottom of the page we found links for social media !!";
        }else {
            message ="We didn't found any media at the bottom of page";

        }
    }

    @Test
    @Order(3)

    public void findInstagramPage()throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement Instagram = webDriver.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/footer/div[2]/div/div[5]/div[2]/div/ul/li[2]/a"));
        Instagram.click();
        Thread.sleep(4000);

        String message = "";

        if (Instagram.isSelected()){
            message ="Welcome at Instagram page of Big Bang !!";
        }else {
            message ="Sorry , that is not Instagram Page !! ";

        }
    }

    @Test
    @Order(4)

    public  void findFacebookPage() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement Facebook = webDriver.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/footer/div[2]/div/div[5]/div[2]/div/ul/li[1]/a"));
        Facebook.click();
        Thread.sleep(4000);

        String message = "";

        if (Facebook.isSelected()){
            message ="Welcome at Instagram page of Big Bang !!";
        }else {
            message ="Sorry , that is not Instagram Page !! ";

        }
    }
    // https://www.linkedin.com/company/big-bang-slovenija/

    @Test
    @Order(5)
    public  void findLinkedINPage() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        webDriver.navigate().to("https://www.linkedin.com/company/big-bang-slovenija/");

        String message = "";

        if (webDriver.getCurrentUrl()=="https://www.linkedin.com/company/big-bang-slovenija/"){
            message ="Welcome at LinkedIN page of Big Bang !!";
        }else {
            message ="Sorry , that is not LinkedIN Page !! ";
        }
    }
}
 */


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import static org.junit.jupiter.api.Assertions.assertTrue;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SocialMediaTests {

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

    public void findPageBottom() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement bottom = webDriver.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/footer/div[2]"));
        bottom.click();
        Thread.sleep(3000);

        String message = "";

        if (bottom.isSelected()){
            message ="On the bottom of the page we found links for social media !!";
        }else {
            message ="We didn't found any media at the bottom of page";

        }
    }

    @Test
    @Order(3)

    public void findInstagramPage()throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement Instagram = webDriver.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/footer/div[2]/div/div[5]/div[2]/div/ul/li[2]/a"));
        Instagram.click();
        Thread.sleep(4000);

        String message = "";

        if (Instagram.isSelected()){
            message ="Welcome to the Instagram page of Big Bang !!";
        }else {
            message ="Sorry , that is not Instagram Page !! ";

        }
    }

    @Test
    @Order(4)

    public  void findFacebookPage() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        WebElement Facebook = webDriver.findElement(By.xpath("//*[@id=\"html-body\"]/div[2]/footer/div[2]/div/div[5]/div[2]/div/ul/li[1]/a"));
        Facebook.click();
        Thread.sleep(4000);

        String message = "";

        if (Facebook.isSelected()){
            message ="Welcome to the Facebook page of Big Bang !!";
        }else {
            message ="Sorry , that is not Facebook Page !! ";

        }
    }

    // https://www.linkedin.com/company/big-bang-slovenija/

    @Test
    @Order(5)
    public  void findLinkedINPage() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        webDriver.navigate().to("https://www.linkedin.com/company/big-bang-slovenija/");

        String message = "";

        if (webDriver.getCurrentUrl()=="https://www.linkedin.com/company/big-bang-slovenija/"){
            message ="Welcome at LinkedIN page of Big Bang !!";
        }else {
            message ="Sorry , that is not LinkedIN Page !! ";
        }
    }
}