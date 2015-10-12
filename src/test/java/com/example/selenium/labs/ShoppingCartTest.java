package com.example.selenium.labs;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ShoppingCartTest {

    private FirefoxDriver browser;

    @Before
    public void setUp() throws Exception {
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void testShoppingCart() {
        browser.get("https://XXXX-YYYY-DDDD.spree.mx/");
        browser.findElement(By.cssSelector("div.container > nav.columns.seven > #main-nav-bar > #shop-link > a > span.translation_missing")).click();
        browser.findElement(By.linkText("Ruby on Rails Tote")).click();
        browser.findElement(By.id("add-to-cart-button")).click();
        browser.findElement(By.linkText("CONTINUE SHOPPING")).click();
        browser.findElement(By.xpath("//aside[@id='sidebar']//a[.='Spree']")).click();
        browser.findElement(By.linkText("Spree Ringer T-Shirt")).click();
        browser.findElement(By.id("add-to-cart-button")).click();
        if (!browser.findElement(By.tagName("html")).getText().contains("$35.98")) {
            System.out.println("verifyTextPresent failed");
        }
        browser.findElement(By.linkText("CONTINUE SHOPPING")).click();
        browser.findElement(By.linkText("Ruby on Rails Tote")).click();
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
