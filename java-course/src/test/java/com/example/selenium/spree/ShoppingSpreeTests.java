package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.pages.CartPage;
import com.example.selenium.spree.pages.HomePage;
import com.example.selenium.spree.pages.ProductPage;
import com.example.selenium.spree.support.LogbackUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
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
    public void testHomePage() {
        HomePage homePage = Pages.openHomePage(webDriver);
        homePage.validateTitle();
        homePage.validateUrl();
    }

    @Test
    public void testDepartmentsCombo() {
        HomePage homePage = Pages.openHomePage(webDriver);
        WebElement depCmb = homePage.getDepartmentCmb();
        String label = depCmb.getAttribute("aria-label");
        boolean isDisplayed = depCmb.isDisplayed();
        boolean isEnabled = depCmb.isEnabled();
        boolean isSelected = depCmb.isSelected();
    }

    @Test
    public void testCapabilities() {
        Capabilities capabilities = webDriver.getCapabilities();
        String name = capabilities.getBrowserName().toLowerCase();
        String version = capabilities.getVersion();
        String os = capabilities.getPlatform().name();
        boolean js = capabilities.isJavascriptEnabled();

        switch (driverType) {
            case Chrome:
                Assert.assertEquals(name, "chrome");
                break;
            case Firefox:
                Assert.assertEquals(name, "firefox");
                break;
            case Safari:
                Assert.assertEquals(name, "safari");
                break;
        }
    }

    @Test
    public void testCartPage() {
        CartPage cartPage = Pages.openCartPage(webDriver);
        cartPage.validateTitle();
        cartPage.validateUrl();
    }

    @Test
    public void testProductPage() {
        ProductPage prodPage = Pages.openProductPage(webDriver);
        prodPage.validateTitle();
        prodPage.validateUrl();
    }

    @Test
    public void testBackAndForth() {
        webDriver.navigate().to("https://selenium.jacobparr.com/products/spree-bag");
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");

        webDriver.navigate().to("https://selenium.jacobparr.com/products/spree-tote");
        Assert.assertEquals(webDriver.getTitle(), "Spree Tote - Spree Demo Site");

        webDriver.navigate().back();
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");

        webDriver.navigate().forward();
        Assert.assertEquals(webDriver.getTitle(), "Spree Tote - Spree Demo Site");
    }

    @Test
    public void testRefresh() {
        webDriver.navigate().to("https://selenium.jacobparr.com/products/spree-bag");
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");

        webDriver.navigate().refresh();
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");
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
