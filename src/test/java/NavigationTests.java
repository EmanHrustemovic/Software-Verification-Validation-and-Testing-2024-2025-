import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NavigationTests {

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
    public void testValidNavigationLinks() {
        // Added all the navigation links with the expected titles
        String[][] navLinks = {
                {"https://www.bigbang.ba/gaming.html", "Gaming"},
                {"https://www.bigbang.ba/racunala-i-periferija.html", "Informatika"},
                {"https://www.bigbang.ba/komunikacije.html", "Mobiteli, tableti i satovi"},
                {"https://www.bigbang.ba/televizori.html", "Televizori i audio"},
                {"https://www.bigbang.ba/bijela-tehnika.html", "Veliki kućanski uređaji"},
                {"https://www.bigbang.ba/mali-kucanski-aparati.html", "Mali kućanski uređaji"},
                {"https://www.bigbang.ba/osobna-njega.html", "Osobna njega"},
                {"https://www.bigbang.ba/foto-i-video.html", "Dronovi, kamere i navigacije"},
                {"https://www.bigbang.ba/dom-vrt-i-alati.html", "Dom, vrt i alati"},
                {"https://www.bigbang.ba/outlet.html", "Outlet"},
                {"https://www.bigbang.ba/nacini-placanja", "Načini plaćanja"},
                {"https://www.bigbang.ba/nacini-dostave", "Načini dostave"},
                {"https://www.bigbang.ba/info-centar", "Info Centar"},
                {"https://www.bigbang.ba/b2b-bb", "B2B ponude ili financiranje/najam"},
                {"https://www.bigbang.ba/promotions", "Promocije"},
                {"https://www.bigbang.ba/brands", "Brands"},
                {"https://www.bigbang.ba/newsletter-prijava", "Newsletter"},
                {"https://www.bigbang.ba/privatnost-podataka", "Privatnost podataka"},
                {"https://www.bigbang.ba/podrska", "Podrška"},
                {"https://www.bigbang.ba/uvjeti-koristenja", "Uvjeti korištenja"},
                {"https://www.bigbang.ba/o-nama", "O nama"},
                {"https://www.bigbang.ba/trgovine", "Trgovine"}
        };

        for (String[] link : navLinks) {
            webDriver.get(link[0]);  // Navigate to the URL
            assertEquals(link[1], webDriver.getTitle().trim(), "Title mismatch for URL: " + link[0]);
        }
    }

    @Test
    @Order(2)
    public void testInvalidNavigationLinks() {
        // Testing invalid links and checking error messages
        String[][] invalidNavLinks = {
                {"https://www.bigbang.ba/sokovi.html", "We could not find anything for sokovi"},
                {"https://www.bigbang.ba/makeup.html", "We could not find anything for makeup"}
        };

        for (String[] link : invalidNavLinks) {
            webDriver.get(link[0]);
            String actualMessage = webDriver.findElement(By.cssSelector("div.message.notice > div")).getText().trim();
            assertTrue(actualMessage.contains(link[1]), "Error message mismatch for: " + link[0]);
        }
    }


    @Test
    @Order(3)
    public void testSocialMediaLinks() {
        webDriver.get("https://www.bigbang.ba");

        // Instagram link
        WebElement instagramLink = webDriver.findElement(By.cssSelector("a.instagram"));
        assertEquals("https://www.instagram.com/bigbangbih/", instagramLink.getAttribute("href"), "Instagram link is incorrect");

        // Facebook link
        WebElement facebookLink = webDriver.findElement(By.cssSelector("a.fb"));
        assertEquals("https://www.facebook.com/bigbangbih", facebookLink.getAttribute("href"), "Facebook link is incorrect");
    }

    @AfterAll
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}

