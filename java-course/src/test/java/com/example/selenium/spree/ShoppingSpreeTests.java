package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ShoppingSpreeTests implements ITest {
    enum DriverType {Chrome, Firefox, Safari}

    private RemoteWebDriver webDriver;
    private DriverType driverType;
    private String testName;

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

    @Override
    public String getTestName() {
        return testName;
    }

    @BeforeClass
    public void beforeClass() {
        System.out.println("Before class");
    }

    // Runs before each test
    // Here we need to create the webDriver
    @BeforeMethod
    public void beforeMethod(Method aMethod) {
        System.out.println("Before method");
        testName = aMethod.getName() + " [" + driverType + "]";

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
    public void testCapabilities() {
        // LAB 10.e
    }

    @Test
    public void testCartPage() {
        // LAB 11.a
    }

    @Test
    public void testBackAndForth() {
        // LAB 11.b
    }

    @Test
    public void testRefresh() {
        // LAB 11.c
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
    public void afterMethod(ITestResult results) throws IOException {
        System.out.println("After method\n");

        if (!results.isSuccess()) {
            File screenShotDir = new File("test-failures");
            screenShotDir.mkdirs();
            File tempFile = webDriver.getScreenshotAs(OutputType.FILE);

            String className = getClass().getSimpleName();
            String methodName = results.getMethod().getMethodName();
            String fileName = String.format("%s.%s [%s].png", className, methodName, driverType);
            FileUtils.copyFile(tempFile, new File(screenShotDir, fileName));
        }

        webDriver.quit();
    }

    @AfterClass
    public void afterClass() {
        System.out.println("After class");
    }
}
