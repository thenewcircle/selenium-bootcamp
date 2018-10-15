package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class ShoppingSpreeTests {
    enum DriverType {Chrome, Firefox, Safari}

    private RemoteWebDriver webDriver;
    private DriverType driverType;

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    @Factory()
    public static Object[] testFactory() {
        List<ShoppingSpreeTests> data = new ArrayList<>();
        data.add(new ShoppingSpreeTests(DriverType.Safari));
        data.add(new ShoppingSpreeTests(DriverType.Firefox));
        data.add(new ShoppingSpreeTests(DriverType.Chrome));
        return data.toArray();
    }

    public ShoppingSpreeTests(DriverType driverType) {
        this.driverType = driverType;
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before class");
    }

    // Runs before each test
    // Here we need to create the webDriver
    @BeforeMethod
    public void beforeMethod() {
        System.out.println("Before method");

        switch (this.driverType) {
            case Chrome:
                webDriver = new ChromeDriver();
                break;
            case Firefox:
                System.setProperty("webdriver.gecko.driver", System.getenv("GECKO_DRIVER"));
                webDriver = new FirefoxDriver();
                break;
            case Safari:
                webDriver = new SafariDriver();
                break;
        }
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
        Assert.assertEquals(2, 2);
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

    @AfterClass
    public void afterClass() {
        System.out.println("After class");
    }
}
