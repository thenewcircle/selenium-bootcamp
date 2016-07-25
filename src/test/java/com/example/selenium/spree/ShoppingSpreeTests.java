package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

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

    @Factory()
    public static Object[] testFactory() {
        List<ShoppingSpreeTests> data = new ArrayList<>();
        data.add(new ShoppingSpreeTests(DriverType.Chrome));
        data.add(new ShoppingSpreeTests(DriverType.IE));
        // data.add(new ShoppingSpreeTests(DriverType.Safari));
        data.add(new ShoppingSpreeTests(DriverType.Firefox));
        return data.toArray();
    }

    public void beforeMethod() throws Exception {
        if (DriverType.Safari == driverType) {
            webDriver = new SafariDriver();

        } else if (DriverType.Firefox == driverType) {
            webDriver = new FirefoxDriver();

        } else if (DriverType.Chrome == driverType) {
            String path = System.getenv("webdriver.chrome.driver");
            System.setProperty("webdriver.chrome.driver", path);
            webDriver = new ChromeDriver();

        } else if (DriverType.IE == driverType) {
            String path = System.getenv("webdriver.ie.driver");
            System.setProperty("webdriver.ie.driver", path);
            webDriver = new InternetExplorerDriver();

        } else {
            throw new UnsupportedOperationException(driverType.name());
        }
    }


    @Test
    public void testHomePage() {
        webDriver.get("http://spree.newcircle.com");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }
}
