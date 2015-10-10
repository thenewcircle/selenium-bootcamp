package com.example.selenium.smoketest.spree;

import org.junit.After;
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
        wd.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void testVerifyJerseyPrice() {
        wd.get(GlobalConfiguration.SHOP_ADDRESS);

        long start = System.currentTimeMillis();
        while (System.currentTimeMillis() - start < 60*1000) {
            if ("Spree Demo Site".equals(wd.getTitle())) {
                break;
            }
        }

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
