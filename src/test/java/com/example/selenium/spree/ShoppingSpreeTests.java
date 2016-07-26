package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
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
    public void testCapabilities() {

        Capabilities capabilities = webDriver.getCapabilities();

        String name = capabilities.getBrowserName();
        if (webDriver instanceof ChromeDriver) {
            Assert.assertEquals(name, "chrome");

        } else if (webDriver instanceof InternetExplorerDriver) {
            Assert.assertEquals(name, "internet explorer");

        } else if (webDriver instanceof FirefoxDriver) {
            Assert.assertEquals(name, "firefox");

        } else {
            throw new UnsupportedOperationException("The browser is not supported: " + name);
        }


        if ("chrome".equals(name)) {
            String version = capabilities.getVersion();
            Assert.assertEquals(version, "51.0.2704.103");

        } else if ("internet explorer".equals(name)) {
            String version = capabilities.getVersion();
            Assert.assertEquals(version, "11");

        } else if ("firefox".equals(name)) {
            String version = capabilities.getVersion();
            Assert.assertEquals(version, "47.0.1");

        } else {
            throw new UnsupportedOperationException("The browser is not supported: " + name);
        }


        if (DriverType.Chrome == driverType) {
            Platform platform = capabilities.getPlatform();
            Assert.assertEquals(platform.name(), "XP");
            Assert.assertEquals(platform.family().name(), "WINDOWS");
            Assert.assertEquals(platform.getMajorVersion(), 10);
            Assert.assertEquals(platform.getMinorVersion(), 0);

        } else if (DriverType.IE == driverType){
            Platform platform = capabilities.getPlatform();
            Assert.assertEquals(platform.name(), "WINDOWS");
            Assert.assertEquals(platform.family().name(), "ANY");
            Assert.assertEquals(platform.getMajorVersion(), 10);
            Assert.assertEquals(platform.getMinorVersion(), 0);

        } else if (DriverType.Firefox == driverType){
            Platform platform = capabilities.getPlatform();
            Assert.assertEquals(platform.name(), "WINDOWS");
            Assert.assertEquals(platform.family().name(), "ANY");
            Assert.assertEquals(platform.getMajorVersion(), 10);
            Assert.assertEquals(platform.getMinorVersion(), 0);

        } else {
            throw new UnsupportedOperationException("The browser is not supported: " + name);
        }

        boolean enabled = capabilities.isJavascriptEnabled();
        Assert.assertTrue(enabled);
    }

    @Test
    public void testHomePage() {
        HomePage homePage = Pages.openHomePage(webDriver);
        homePage.validateTitle();
    }

    @Test
    public void testCartPageTitle() {
        CartPage cartPage = Pages.openCartPage(webDriver);
        cartPage.validateTitle();
    }

    @Test(dependsOnMethods = "testCartPageTitle")
    public void testBackAndForth() throws Exception {
        webDriver.get("http://spree.newcircle.com/products/spree-bag");
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");
        Thread.sleep(1000);

        webDriver.navigate().to("http://spree.newcircle.com/products/spree-tote");
        Assert.assertEquals(webDriver.getTitle(), "Spree Tote - Spree Demo Site");
        Thread.sleep(1000);

        webDriver.navigate().back();
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");
        Thread.sleep(1000);

        webDriver.navigate().forward();
        Assert.assertEquals(webDriver.getTitle(), "Spree Tote - Spree Demo Site");
        Thread.sleep(1000);

        Assert.assertTrue(false);
    }

    @Test(dependsOnMethods = "testBackAndForth")
    public void testRefresh() throws Exception {
        webDriver.navigate().to("http://spree.newcircle.com/products/spree-bag");
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");
        Thread.sleep(1000);

        webDriver.navigate().refresh();
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");
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
    public void afterMethod(ITestResult results) throws IOException {
        try {
            if (results.isSuccess() == false) {
                File screenShotDir = new File("\\tmp\\test-failures");
                screenShotDir.mkdirs();

                File tempFile = webDriver.getScreenshotAs(OutputType.FILE);

                String fileName = String.format("%s.%s [%s].png",
                        getClass().getSimpleName(),
                        results.getMethod().getMethodName(),
                        driverType);

                FileUtils.copyFile(tempFile, new File(screenShotDir, fileName));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

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
