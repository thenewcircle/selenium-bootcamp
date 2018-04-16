package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ShoppingSpreeTests implements ITest {
    String projectPath = "/Users/gilzhaiek/projects/nc/selenium-bootcamp-java";
    String chromeDriverPath = projectPath + "/chromedriver";
    String firefoxDriverPath = projectPath + "/geckodriver";

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

        if (driverType == DriverType.Chrome) {
            System.out.println("Before method\n");
            System.setProperty("webdriver.chrome.driver", chromeDriverPath);
            webDriver = new ChromeDriver();

        } else if (driverType == DriverType.Firefox) {
            System.out.println("Before method\n");
            System.setProperty("webdriver.gecko.driver", firefoxDriverPath);
            webDriver = new FirefoxDriver();
            Capabilities capabilities = webDriver.getCapabilities();
            Assert.assertEquals(capabilities.getBrowserName(), "firefox");
        }
    }

    @Test
    public void testCapabilities() {
        Capabilities capabilities = webDriver.getCapabilities();
        if (driverType == DriverType.Chrome) {
            Assert.assertEquals(capabilities.getBrowserName(), "chrome");
        } else if (driverType == DriverType.Firefox) {
            Assert.assertEquals(capabilities.getBrowserName(), "firefox");
        }
    }

    @Test
    public void testGetGoogleUrl() {
        webDriver.get("http://google.com");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Google");
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, "https://www.google.com/?gws_rd=ssl",
                "Got URL:" + url + " but expected https://www.google.com/?gws_rd=ssl");
        Assert.assertTrue(url.startsWith("https://www.example.com"),
                "URL:" + url + " did not start with https://www.google.com");
    }

    @Test
    public void testHomePage() {
        webDriver.get("https://selenium.jacobparr.com");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    @Test
    public void testCiscoPage() {
        webDriver.get("https://cisco.com");
        String title = webDriver.getTitle();
        System.out.println(title);
        String source = webDriver.getPageSource();
        Assert.assertTrue(source.contains("<!--"));
    }

    @AfterMethod
    public void afterMethod(ITestResult results) throws Exception {
        System.out.println("After method\n");

        if (results.getStatus() == ITestResult.FAILURE) {
            File screenShotDir = new File(projectPath + "/test-failures");
            screenShotDir.mkdirs();
            File tempFile = webDriver.getScreenshotAs(OutputType.FILE);
            String className = getClass().getSimpleName();
            String methodName = results.getMethod().getMethodName();
            String fileName = String.format("%s.%s [%s].png", className, methodName, driverType);
            FileUtils.copyFile(tempFile, new File(screenShotDir, fileName));
        }

        webDriver.quit();
    }

}
