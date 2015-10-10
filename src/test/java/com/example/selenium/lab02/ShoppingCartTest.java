package com.example.selenium.lab02;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShoppingCartTest {
    FirefoxDriver browser;
    
    @Before
    public void setUp() throws Exception {
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().window().maximize();
    }

    @Test
    public void testShoppingCart() {
        browser.get("https://superb-store-8178.spree.mx/");
        browser.findElement(By.linkText("SHOP")).click();
        browser.findElement(By.cssSelector("img[alt=\"Ruby on Rails Tote\"]")).click();
        browser.findElement(By.id("add-to-cart-button")).click();
        browser.findElement(By.linkText("CONTINUE SHOPPING")).click();
        browser.findElement(By.linkText("Spree")).click();
        browser.findElement(By.cssSelector("img[alt=\"Spree Ringer T-Shirt\"]")).click();
        browser.findElement(By.id("add-to-cart-button")).click();
        if (!browser.findElement(By.tagName("html")).getText().contains("$35.98")) {
            System.out.println("verifyTextPresent failed");
        }
        browser.findElement(By.linkText("CONTINUE SHOPPING")).click();
        browser.findElement(By.cssSelector("img[alt=\"Ruby on Rails Tote\"]")).click();
        browser.findElement(By.cssSelector("div.page-wrapper")).click();
        browser.findElement(By.linkText("Featured")).click();
        if (!browser.findElement(By.id("Price_Range_$15.00_-_$18.00")).isSelected()) {
            browser.findElement(By.id("Price_Range_$15.00_-_$18.00")).click();
        }
        browser.findElement(By.name("button")).click();
        browser.findElement(By.cssSelector("a.cart-info.full")).click();
        if (!browser.findElement(By.tagName("html")).getText().contains("$35.98")) {
            System.out.println("verifyTextPresent failed");
        }
    }
    
    @After
    public void tearDown() {
        browser.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}
