import org.junit.Assert;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Duration;
import java.util.List;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CartTest {
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
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Order(1)
    @Test

    public void loginAndOpenCart()throws InterruptedException{
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

        webDriver.navigate().to("https://www.bigbang.ba/checkout/cart/");

    }

    @Order(2)
    @Test
    public void addingIntoCart() {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        webDriver.navigate().to("https://www.bigbang.ba");


        // Klik na prvi proizvod
        WebElement tvsEl = webDriver.findElement(By.linkText("TELEVIZORI"));
        tvsEl.click();

        WebElement product = webDriver.findElement(By.className("product-item-photo"));
        product.click();

        WebElement addProduct = webDriver.findElement(By.id("product-addtocart-button"));
        addProduct.click();


        WebElement cart = webDriver.findElement(By.className("minicart-wrapper"));
        cart.click();


        List<WebElement> list = webDriver.findElements(By.xpath("//*[contains(text(),'" + "Artikl" + "')]"));
        Assertions.assertTrue(!list.isEmpty(), "Text not found!");
    }

    @Order(3)
    @Test

    public void checkingCartStatus() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        //webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        String currentURL = webDriver.getCurrentUrl();
        if (!currentURL.contains("account")) {
            System.out.println("Login was not successful. We are currently at: " + currentURL);
            return;
        }

        webDriver.navigate().to("https://www.bigbang.ba/checkout/cart/");
        Thread.sleep(8000);
    }

    @Order(4)
    @Test
    public void updatingCart() throws InterruptedException{
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        String currentURL = webDriver.getCurrentUrl();
        if (!currentURL.contains("account")) {
            System.out.println("Login was not successful. We are currently at: " + currentURL);
            return;
        }
        webDriver.navigate().to("https://www.bigbang.ba/checkout/cart/");
        Thread.sleep(8000);

        WebElement quantityInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.input-text.qty")));
        quantityInput.clear();
        quantityInput.sendKeys("3");

        WebElement updateButton = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.action.update")));
        updateButton.click();
        System.out.println("Cart updated successfully.");
    }

    @Order(5)
    @Test
    public void verifyTotalPrice() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/customer/account/login/");

        webDriver.navigate().to("https://www.bigbang.ba/checkout/cart/");
        Thread.sleep(3000); // Čekanje za učitavanje stranice (zamijeniti sa WebDriverWait ako je moguće)

        List<WebElement> rows = webDriver.findElements(By.cssSelector("table#shopping-cart-table tbody tr"));
        BigDecimal calculatedTotal = BigDecimal.ZERO;

        for (int i = 0; i < rows.size(); i++) {
            WebElement row = rows.get(i);
            try {
                System.out.println("Obrađujem red " + (i + 1));

                List<WebElement> priceElements = row.findElements(By.cssSelector(".price"));
                if (priceElements.isEmpty()) {
                    System.out.println("Element '.price' nije pronađen u redu " + (i + 1));
                    continue;
                }

                WebElement priceElement = priceElements.get(0);
                String priceText = priceElement.getText().replace(" KM", "").trim();
                System.out.println("Price Text: " + priceText);

                priceText = priceText.replace(".", "").replace(",", ".");
                BigDecimal price = new BigDecimal(priceText);

                List<WebElement> quantityElements = row.findElements(By.cssSelector("input.qty"));
                if (quantityElements.isEmpty()) {
                    System.out.println("Element 'input.qty' nije pronađen u redu " + (i + 1));
                    continue;
                }

                WebElement quantityElement = quantityElements.get(0);
                int quantity = Integer.parseInt(quantityElement.getAttribute("value"));
                System.out.println("Quantity: " + quantity);

                calculatedTotal = calculatedTotal.add(price.multiply(BigDecimal.valueOf(quantity)));
            } catch (Exception e) {
                System.out.println("Greška u redu " + (i + 1) + ": " + e.getMessage());
            }
        }

        WebElement totalPriceElement = webDriver.findElement(By.xpath("//*[@id=\"shopping-cart-table\"]/tbody/tr[1]/td[4]/span/span/span"));
        String totalPriceText = totalPriceElement.getText().replace("KM", "").trim();
        totalPriceText = totalPriceText.replace(".", "").replace(",", ".");
        BigDecimal displayedTotal = new BigDecimal(totalPriceText);

        calculatedTotal = calculatedTotal.setScale(2, RoundingMode.HALF_UP);
        displayedTotal = displayedTotal.setScale(2, RoundingMode.HALF_UP);

        if (calculatedTotal.compareTo(displayedTotal) == 0) {
            System.out.println("Total price verification passed! Calculated: " + calculatedTotal + ", Displayed: " + displayedTotal);
        } else {
            System.out.println("Total price verification failed! Calculated: " + calculatedTotal + ", Displayed: " + displayedTotal);
            Assertions.fail("Total price mismatch.");
        }
    }



    @Order(7)
    @Test
    public void removingItemsFromTheCart() throws InterruptedException {
        webDriver.get("https://www.bigbang.ba/customer/account/login/");
        webDriver.manage().window().maximize();
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        String currentURL = webDriver.getCurrentUrl();
        if (!currentURL.contains("account")) {
            System.out.println("Login was not successful. We are currently at: " + currentURL);
            return;
        }

        webDriver.navigate().to("https://www.bigbang.ba/checkout/cart/");
        Thread.sleep(3000);

        List<WebElement> removeButtons = webDriver.findElements(By.cssSelector("a.action-delete"));
        if (!removeButtons.isEmpty()) {
            for (WebElement removeButton : removeButtons) {
                wait.until(ExpectedConditions.elementToBeClickable(removeButton)).click();
                Thread.sleep(2000);
            }
            System.out.println("All items removed from the cart.");
        } else {
            System.out.println("No items to remove.");
        }
    }

    @Order(6)
    @Test

    public void processPayment() throws InterruptedException {
        verifyTotalPrice();
        webDriver.manage().window().maximize();

        webDriver.navigate().to("https://www.bigbang.ba/checkout/cart/");
        Thread.sleep(2000);

        WebElement continueToCheckoutButton = webDriver.findElement(By.xpath("//*[@id=\"maincontent\"]/div[3]/div/div[3]/div[1]/ul/li/button"));
        continueToCheckoutButton.click();
        Thread.sleep(6000);

        WebElement paymentMethod = webDriver.findElement(By.cssSelector("#checkout-step-shipping > div.field.addresses > div > div > div.shipping-address-item.selected-item"));
        paymentMethod.click();
        Thread.sleep(2000);

        WebElement addressField = webDriver.findElement(By.xpath("//*[@id=\"shipping-method-buttons-container\"]/div/button"));
        addressField.click();
        Thread.sleep(8000);

        WebElement card = webDriver.findElement(By.xpath("//*[@id=\"pikpay\"]"));
        card.click();
        Thread.sleep(7000);

    }

}

