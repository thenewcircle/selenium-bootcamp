package com.example.selenium.smoketest.spree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

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
        wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void testVerifyJerseyPrice() {
        wd.get(GlobalConfiguration.SHOP_ADDRESS+"/products");
        Assert.assertEquals("Spree Demo Site", wd.getTitle());
        Assert.assertTrue(wd.findElement(By.cssSelector("#product_6 > .price")).getText().contentEquals("$19.99"));
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
