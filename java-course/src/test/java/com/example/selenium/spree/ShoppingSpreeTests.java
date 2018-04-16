package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
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
    enum DriverType {Chrome, Firefox}

    DriverType driverType;
    RemoteWebDriver webDriver;
    String testName;

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    public ShoppingSpreeTests(DriverType driverType) {
        this.driverType = driverType;
    }

    @Override
    public String getTestName() {
        return testName;
    }

    @Factory()
    public static Object[] testFactory() {
        List<ShoppingSpreeTests> data = new ArrayList<>();
        data.add(new ShoppingSpreeTests(DriverType.Firefox));
        data.add(new ShoppingSpreeTests(DriverType.Chrome));
        return data.toArray();
    }

    @BeforeMethod
    public void beforeMethod(Method aMethod) throws Exception {
        testName = aMethod.getName() + " [" + driverType + "]";

        if(driverType == DriverType.Chrome) {
            System.out.println("Before method\n");
            System.setProperty("webdriver.chrome.driver",
                    "/Users/gilzhaiek/projects/nc/selenium-bootcamp-java/chromedriver");
            webDriver = new ChromeDriver();
        } else if(driverType == DriverType.Firefox) {
            System.out.println("Before method\n");
            System.setProperty("webdriver.gecko.driver",
                    "/Users/gilzhaiek/projects/nc/selenium-bootcamp-java/geckodriver");
            webDriver = new FirefoxDriver();
        }
    }

    @Test
    public void testHomePage() {
        webDriver.get("https://selenium.jacobparr.com");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    @Test
    public void tetCiscoPage() {
        webDriver.get("https://cisco.com");
        String title = webDriver.getTitle();
        System.out.println(title);
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
