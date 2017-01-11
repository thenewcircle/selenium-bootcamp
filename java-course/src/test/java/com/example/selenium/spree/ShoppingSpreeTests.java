package com.example.selenium.spree;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;

import java.util.ArrayList;
import java.util.List;

public class ShoppingSpreeTests {

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    enum DriverType { Chrome, Firefox, IE, Safari, Edge }

    DriverType driverType;
    RemoteWebDriver webDriver;

    public ShoppingSpreeTests(DriverType driverType) {
        this.driverType = driverType;
    }

    @BeforeMethod
    public void beforeMethod() throws Exception {
        // System.setProperty("webdriver.gecko.driver", "c:\\tools\\selenium\\geckodriver.exe");
        // webDriver = new FirefoxDriver();

        String path = System.getenv("webdriver_chrome_driver");
        System.setProperty("webdriver.chrome.driver", path);
        webDriver = new ChromeDriver();

        // String path = System.getenv("webdriver_ie_driver");
        // System.setProperty("webdriver.ie.driver", path);
        // webDriver = new InternetExplorerDriver();

        // webDriver = new SafariDriver();
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

    @Factory()
    public static Object[] testFactory() {
        List<ShoppingSpreeTests> data = new ArrayList<>();
        data.add(new ShoppingSpreeTests(DriverType.Chrome));
        data.add(new ShoppingSpreeTests(DriverType.Firefox));
        // data.add(new ShoppingSpreeTests(DriverType.IE));
        // data.add(new ShoppingSpreeTests(DriverType.Safari));
        return data.toArray();
    }
}