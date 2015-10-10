package com.example.shopping;

import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 * Selenium smoke test.
 * 
 * @author @nevenc
 *
 */
public class RunSeleniumSmokeTest {

    FirefoxDriver wd;

    @Before
    public void setUp() throws Exception {
        wd = new FirefoxDriver();
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void testVerifyJerseyPrice() {
        wd.get(GlobalConfiguration.SHOP_ADDRESS);
        wd.findElement(By.cssSelector("#product_6 > div.product-image > a > img[alt=\"Ruby Baseball Jersey\"]")).click();
        if (!wd.findElement(By.tagName("html")).getText().contains("$19.99")) {
           fail("verifyTextPresent failed");
        }
    }

    @After
    public void tearDown() {
        wd.quit();
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
