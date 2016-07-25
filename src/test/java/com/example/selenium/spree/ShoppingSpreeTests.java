package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ShoppingSpreeTests implements ITest {

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    enum DriverType { Chrome, Firefox, IE, Safari, Edge }
    DriverType driverType;

    RemoteWebDriver webDriver;
    String testName;

    public ShoppingSpreeTests(DriverType driverType) {
        this.driverType = driverType;
    }

    @Override
    public String getTestName() {
        return testName;
    }

    @BeforeMethod
    public void beforeMethod(Method aMethod) throws Exception {
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

        testName = aMethod.getName() + " [" + driverType + "]";
    }

    @Test
    public void testHomePage() {
        webDriver.get("http://spree.newcircle.com");

        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    @Test
    public void testGetGoogleUrl() {
        webDriver.get("http://google.com");

        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Google");

        String url = webDriver.getCurrentUrl();
        Assert.assertTrue(url.startsWith("https://www.google.com"), "Found " + url);
    }

    @Test
    public void testNoComments() {
        webDriver.get("http://www.example.com");
        String source = webDriver.getPageSource();
        Assert.assertFalse(source.contains("<!--"), "Found " + source);
    }

    @AfterMethod
    public void afterMethod() {
        webDriver.quit();
    }

    @Factory()
    public static Object[] testFactory() {
        List<ShoppingSpreeTests> data = new ArrayList<>();
        data.add(new ShoppingSpreeTests(DriverType.Chrome));
        // data.add(new ShoppingSpreeTests(DriverType.IE));
        // data.add(new ShoppingSpreeTests(DriverType.Safari));
        // data.add(new ShoppingSpreeTests(DriverType.Firefox));
        return data.toArray();
    }
}
