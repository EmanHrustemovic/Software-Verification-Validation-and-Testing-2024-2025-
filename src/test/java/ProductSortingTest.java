import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class ProductSortingTest {
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

    public void redirectingToProductsPage() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        String currentUrl = webDriver.getCurrentUrl();
        System.out.println("We are currently at : " + currentUrl);
        //assertEquals("https://www.bigbang.ba/customer/account/login/",currentUrl);
        webDriver.navigate().to("https://www.bigbang.ba/televizori/led-tv/tv-32-ili-manji.html");
        System.out.println("We are redirected successfully to the login page : 'https://www.bigbang.ba/televizori/led-tv/tv-32-ili-manji.html'");
        Thread.sleep(6000);
    }


    /*

    @Test
    @Order(2)

    public <JavascriptExecutor> void sortingByPrice () throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/televizori/led-tv/tv-32-ili-manji.html");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions  actions = new Actions(webDriver);

        WebElement leftSlider = webDriver.findElement(By.xpath("//*[@id=\"am-shopby-filter-price_6776c8a740ee9\"]/div[1]/span[1]"));
        WebElement rightSlider = webDriver.findElement(By.xpath("//*[@id=\"am-shopby-filter-price_6776c8a740ee9\"]/div[1]/span[2]"));

        actions.clickAndHold(leftSlider).moveByOffset(70,0).release().perform();
        Thread.sleep(8000);
        actions.clickAndHold(rightSlider).moveByOffset(-50,0).release().perform();
        Thread.sleep(8000);

        WebElement price = webDriver.findElement(By.xpath("//*[@id=\"am-shopby-filter-price_6776c8a740ee9\"]/div[2]"));

        String message = "We adjusted our price in range  : ";
        System.out.println(  message + price);
    }*/

    @Test
    @Order(2)

    public  <JavascriptExecutor> void sortingByBrand() throws InterruptedException{

        webDriver.get("https://www.bigbang.ba/televizori/led-tv/tv-32-ili-manji.html");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions  actions = new Actions(webDriver);

        WebElement brand = webDriver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[2]/div[2]/form/ol/li[1]"));
        brand.click();
        Thread.sleep(4000);

        // Dohvati prvi televizor iz rezultata pretrage
        WebElement firstProduct = webDriver.findElement(By.cssSelector(".products-grid .product-item:first-child"));

        // Provjeri da li prvi televizor sadrži naziv LG
        String productName = firstProduct.getText();

        if (productName.contains("LG")) {
            System.out.println("We found all TV's whose brand is LG");
        } else {
            System.out.println("There are some TV's whose brand is not LG");
        }
    }

    @Test
    @Order(3)

    public <JavascriptExecutor> void  sortingByScreenSize()throws InterruptedException{

        webDriver.get("https://www.bigbang.ba/televizori/led-tv/tv-32-ili-manji.html");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions  actions = new Actions(webDriver);

        WebElement size = webDriver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[4]/div[2]/form"));
        size.click();
        Thread.sleep(4000);

        WebElement firstProduct = webDriver.findElement(By.cssSelector(".products-grid .product-item:first-child"));

        String productName = firstProduct.getText();

        if (productName.contains(" 32 ")) {
            System.out.println("We found all TV's whose size has 32 inches");
        } else {
            System.out.println("We found 0 TV's with size 32");
        }
    }

    @Test
    @Order(4)

    public <JavascriptExecutor> void sortingByType()throws InterruptedException{

        webDriver.get("https://www.bigbang.ba/televizori/led-tv/tv-32-ili-manji.html");
        webDriver.manage().window().maximize();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions  actions = new Actions(webDriver);

        WebElement type = webDriver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[5]/div[2]"));
        type.click();
        Thread.sleep(4000);

        WebElement firstProduct = webDriver.findElement(By.cssSelector(".products-grid .product-item:first-child"));

        String productName = firstProduct.getText();

        if (productName.contains(" Smart TV ")) {
            System.out.println("We found all smart TV");
        } else {
            System.out.println("We didn't found any smart TV");
        }

    }

    @Test
    @Order(5)

    public <JavascriptExecutor> void sortingWashingMachine()throws InterruptedException{

        webDriver.get("https://www.bigbang.ba/bijela-tehnika/perilice-rublja/perilice-rublja.html");
        //webDriver.manage().window().maximize();
        Thread.sleep(3000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        Actions  actions = new Actions(webDriver);

        WebElement type = webDriver.findElement(By.xpath("//*[@id=\"narrow-by-list\"]/div[5]/div[2]"));
        type.click();
        Thread.sleep(4000);

        WebElement firstProduct = webDriver.findElement(By.cssSelector(".products-grid .product-item:first-child"));

        String productName = firstProduct.getText();

        if (productName.contains(" Smart TV ")) {
            System.out.println("We found all smart TV");
        } else {
            System.out.println("We didn't found any smart TV");
        }

    }
}