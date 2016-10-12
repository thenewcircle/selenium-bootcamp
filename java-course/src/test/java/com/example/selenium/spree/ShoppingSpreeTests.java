package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingSpreeTests implements ITest {

    static {
        LogbackUtils.initLogback(Level.WARN);
    }

    enum DriverType {Chrome, Firefox, IE, Safari, Edge}

    String testName;
    DriverType driverType;
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
            webDriver = new FirefoxDriver();

        } else if (DriverType.Chrome == driverType) {
            String path = System.getenv("webdriver_chrome_driver");
            System.setProperty("webdriver.chrome.driver", path);
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-extensions");
            webDriver = new ChromeDriver(options);

        } else if (DriverType.IE == driverType) {
            String path = System.getenv("webdriver_ie_driver");
            System.setProperty("webdriver.ie.driver", path);
            webDriver = new InternetExplorerDriver();

        } else {
            throw new UnsupportedOperationException(driverType.name());
        }

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    }

    @Test
    public void testShoppingSpree() {

        HomePage homePage = Pages.openHomePage(webDriver);

        ProductsPage productsPage = homePage.search("Mug");

        ProductPage productPage = productsPage.clickProductLnk("Spree Mug");
        productPage.validateImageSrc("https://spreecommerce-demo.herokuapp.com/spree/products/45/product/spree_mug.jpeg?");
        productPage.clickThumbnail(1);
        productPage.validateImageSrc("https://spreecommerce-demo.herokuapp.com/spree/products/46/product/spree_mug_back.jpeg?");
        productPage.setQuantity(3);
        productPage.validateCartLink(0, null);
        productPage.clickAddToCart();
        productPage.confirmResponse("Item Added To Cart");
        productPage.validateCartLink(3, "41.97");

        CartPage cartPage = productPage.clickShoppingCart();
        cartPage.validateCartLink(3, "41.97");

        cartPage.clickContinueShopping();
    }

    @Test
    public void testSearchSpree() throws InterruptedException {
        HomePage homePage = Pages.openHomePage(webDriver);

        ProductsPage productsPage = homePage.search("Bag");

        productsPage.validateSearchText("Bag");
        productsPage.clearSearch();
        productsPage.validateSearchText("");

        productsPage.clickLogo();
        Thread.sleep(5*1000);
        productsPage.validateUrl();
    }

    @Test
    public void testDepartmentsCombo() {
        HomePage homePage = Pages.openHomePage(webDriver);
        WebElement depCmb = homePage.getDepartmentCmb();
    }

    @Test
    public void testCapabilities() {
        Capabilities capabilities = webDriver.getCapabilities();

        String name = capabilities.getBrowserName();
        String version = capabilities.getVersion();
        String os = capabilities.getPlatform().name();
        boolean js = capabilities.isJavascriptEnabled();

        if (webDriver instanceof FirefoxDriver) {
            Assert.assertEquals(name, "firefox");
            Assert.assertEquals(version, "47.0.1");
            Assert.assertEquals(os, "WINDOWS");
            Assert.assertTrue(js);

        } else if (webDriver instanceof ChromeDriver) {
            Assert.assertEquals(name, "chrome");
            Assert.assertEquals(version, "53.0.2785.143");
            Assert.assertEquals(os, "XP");
            Assert.assertTrue(js);

        } else if (webDriver instanceof InternetExplorerDriver) {
            Assert.assertEquals(name, "internet explorer");
            Assert.assertEquals(version, "11");
            Assert.assertEquals(os, "WINDOWS");
            Assert.assertTrue(js);
        }
    }

    @Test
    public void testA() {
        Assert.assertTrue(true);
    }

    @Test
    public void testB() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testRefresh() {
        webDriver.navigate().to("https://spreecommerce-demo.herokuapp.com/products/spree-bag");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Bag - Spree Demo Site");

        webDriver.navigate().refresh();
        title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Bag - Spree Demo Site");
    }

    @Test
    public void testBackAndForth() {
        webDriver.navigate().to("https://spreecommerce-demo.herokuapp.com/products/spree-bag");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Bag - Spree Demo Site");

        webDriver.navigate().to("https://spreecommerce-demo.herokuapp.com/products/spree-tote");
        title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");

        webDriver.navigate().back();
        title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Bag - Spree Demo Site");

        webDriver.navigate().forward();
        title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");
    }

    @Test
    public void testCartPage() {
        Pages.openCartPage(webDriver);
    }

    @Test
    public void testProductPage() {
        Pages.openProductPage(webDriver, "Spree Tote");
    }

    @Test
    public void testHomePage() {
        Pages.openHomePage(webDriver);
    }

    @Test
    public void testGetGoogleUrl() {
        webDriver.get("http://google.com");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Google");

        String url = webDriver.getCurrentUrl();
        String msg = "Found " + url;
        Assert.assertTrue(url.startsWith("https://www.google.com/?"), msg);
    }

    @Test
    public void testNoComments() {
        webDriver.get("https://www.example.com");
        String source = webDriver.getPageSource();
        Assert.assertFalse(source.contains("<!--"));
    }

    @AfterMethod
    public void afterMethod(ITestResult results) throws Exception {
        File screenShotDir = new File("\\tmp\\test-failures");
        screenShotDir.mkdirs();
        File tempFile = webDriver.getScreenshotAs(OutputType.FILE);

        String className = getClass().getSimpleName();
        String methodName = results.getMethod().getMethodName();
        String fileName = String.format("%s.%s [%s].png", className, methodName, driverType);

        FileUtils.copyFile(tempFile, new File(screenShotDir, fileName));

        webDriver.quit();
    }

    @Factory()
    public static Object[] testFactory() {
        List<ShoppingSpreeTests> data = new ArrayList<>();
        data.add(new ShoppingSpreeTests(DriverType.IE));
        data.add(new ShoppingSpreeTests(DriverType.Firefox));
        data.add(new ShoppingSpreeTests(DriverType.Chrome));
        // data.add(new ShoppingSpreeTests(DriverType.Safari));
        return data.toArray();
    }
}
