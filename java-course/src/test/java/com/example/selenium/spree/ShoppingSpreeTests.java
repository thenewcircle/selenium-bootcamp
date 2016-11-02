package com.example.selenium.spree;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.*;
import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ShoppingSpreeTests implements ITest {

    enum DriverType { Chrome, Firefox, IE, Safari, Edge }
    DriverType driverType;
    String testName;

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    RemoteWebDriver webDriver;

    public ShoppingSpreeTests(DriverType driverType) {
        this.driverType = driverType;
    }

    @Override
    public String getTestName() {
        return testName;
    }

    @BeforeMethod
    public void beforeMethod(Method aMethod) throws Exception {
        testName = aMethod.getName() + " [" + driverType + "]";

        if (DriverType.Safari == driverType) {
            webDriver = new SafariDriver();

        } else if (DriverType.Firefox == driverType) {
            String path = System.getenv("webdriver_firefox_marionette");
            System.setProperty("webdriver.firefox.marionette", path);
            webDriver = new FirefoxDriver();

        } else if (DriverType.Chrome == driverType) {
            String path = System.getenv("webdriver_chrome_driver");
            System.setProperty("webdriver.chrome.driver", path);
            webDriver = new ChromeDriver();

        } else if (DriverType.IE == driverType) {
            String path = System.getenv("webdriver_ie_driver");
            System.setProperty("webdriver.ie.driver", path);
            webDriver = new InternetExplorerDriver();

        } else {
            throw new UnsupportedOperationException(driverType.name());
        }
    }

    @Test
    public void testA() throws Exception {
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
        data.add(new ShoppingSpreeTests(DriverType.IE));
        // data.add(new ShoppingSpreeTests(DriverType.Safari));
        data.add(new ShoppingSpreeTests(DriverType.Firefox));
        data.add(new ShoppingSpreeTests(DriverType.Chrome));
        return data.toArray();
    }
}
