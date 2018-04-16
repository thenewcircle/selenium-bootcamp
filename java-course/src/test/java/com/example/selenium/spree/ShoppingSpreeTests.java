package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ShoppingSpreeTests {
    RemoteWebDriver webDriver;

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before method\n");
        System.setProperty("webdriver.gecko.driver",
                "/Users/gilzhaiek/projects/nc/selenium-bootcamp-java/geckodriver");
        webDriver = new FirefoxDriver();

//        System.setProperty("webdriver.chrome.driver",
//                "/Users/gilzhaiek/projects/nc/selenium-bootcamp-java/chromedriver");
//        webDriver = new ChromeDriver();

    }

    @Test
    public void testHomePage() {
        webDriver.get("https://selenium.jacobparr.com");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    @Test
    public void testA() {
        System.out.println("Test A");
        Assert.assertTrue(1 == 1);
    }

    @Test
    public void testB() {
        System.out.println("Test B");
        Assert.assertEquals(1, 1);
    }

    @AfterMethod
    public void afterMethod() {
        System.out.println("After method\n");
        webDriver.quit();
    }

}
