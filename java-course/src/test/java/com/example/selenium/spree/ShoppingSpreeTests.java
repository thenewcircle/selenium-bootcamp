package com.example.selenium.spree;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;

public class ShoppingSpreeTests {

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    RemoteWebDriver webDriver;

    @BeforeMethod
    public void beforeMethod() throws Exception {
        System.setProperty("webdriver.gecko.driver", "c:\\tools\\selenium\\geckodriver.exe");
        webDriver = new FirefoxDriver();
    }

    @Test
    public void testHomePage() {
        webDriver.get("https://spreecommerce-demo.herokuapp.com");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    @Test
    public void testA() {
        Assert.assertTrue(true);
    }

    @Test
    public void testB() {
        Assert.assertEquals(1, 1);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

}