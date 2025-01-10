package com.example;

import org.junit.jupiter.api.AfterAll;
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

    @Test
    public void testValidSearch() {
        try {
            webDriver.get("https://www.bigbang.ba");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.amsearch-input")));

            // Search terms
            String[] validSearchTerms = {
                    "iPhone",
                    "PlayStation",
                    "Samsung",
                    "TV",
                    "Kuhalo",
                    "Frižider"
            };

            Actions actions = new Actions(webDriver);

            for (String term : validSearchTerms) {
                searchInput.clear();
                actions.sendKeys(searchInput, term).perform();
                Thread.sleep(3000); // Wait for results to load
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testEmptySearch() {
        try {
            webDriver.get("https://www.bigbang.ba");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.amsearch-input")));

            // Test empty search input
            searchInput.clear();
            Thread.sleep(3000); // Wait for results to reload or handle the empty state

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testInvalidSearch() {
        try {
            webDriver.get("https://www.bigbang.ba");

            WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
            WebElement searchInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input.amsearch-input")));

            String invalidTerm = "asdf123"; // Invalid search term
            Actions actions = new Actions(webDriver);

            searchInput.clear();
            actions.sendKeys(searchInput, invalidTerm).perform();
            Thread.sleep(3000); // Wait for results to load or handle no results

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}