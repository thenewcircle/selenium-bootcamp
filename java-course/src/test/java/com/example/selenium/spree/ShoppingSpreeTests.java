package com.example.selenium.spree;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;
import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ShoppingSpreeTests implements ITest {

    enum DriverType { Chrome, Firefox, IE, Safari, Edge, Grid }
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

        if (DriverType.Grid == driverType) {
            DesiredCapabilities capabilities = new DesiredCapabilities();
//            capabilities.setBrowserName("firefox");

            URL remoteAddress = new URL("http://192.168.101.7:4444/wd/hub");
            webDriver = new RemoteWebDriver(remoteAddress,capabilities);

        } else if (DriverType.Safari == driverType) {
            webDriver = new SafariDriver();

        } else if (DriverType.Firefox == driverType) {
            String path = System.getenv("webdriver_firefox_marionette");
            System.setProperty("webdriver.firefox.marionette", path);
//            FirefoxBinary binary = new FirefoxBinary(new File(""));
//            FirefoxProfile profile = new FirefoxProfile(new File(""));
            webDriver = new FirefoxDriver(/*binary, profile*/);

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

        webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        testName = aMethod.getName() + " [" + driverType + " on " + webDriver.getCapabilities().getPlatform().name() + "]";
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
        productPage.validateCartLink(0, null); // empty cart
        productPage.clickAddToCart();
        productPage.confirmResponse("Item Added To Cart");

        productPage.validateCartLink(3, "41.97"); // 3 items
//        CartPage cartPage = productPage.clickCart();
//        homePage = cartPage.clickContinueShopping();
    }

    @Test
    public void testDepartmentsCombo() {
        HomePage homePage = Pages.openHomePage(webDriver);
        WebElement deptCmb = homePage.getDepartmentCmb();

        String attr = deptCmb.getAttribute("aria-label");
        Assert.assertEquals(attr, "Taxon");

        String color = deptCmb.getCssValue("background-color");
        // Assert.assertEquals(color, "rgba(255, 255, 255, 1)");
        Assert.assertTrue(Arrays.asList("rgba(255, 255, 255, 1)", "rgb(255, 255, 255)").contains(color), "Found " + color);

        Point location = deptCmb.getLocation();
        Assert.assertTrue(location.x > 100, "Found " + location.x);
        Assert.assertTrue(location.y < 200, "Found " + location.y);

        Dimension size = deptCmb.getSize();
        Assert.assertTrue(size.width > 100 && size.width < 200, "Found " + size.width);
        Assert.assertTrue(size.height > 15 && size.height < 35, "Found " + size.height);

        String tagName = deptCmb.getTagName();
        Assert.assertEquals(tagName, "select");

        if (driverType != DriverType.Safari) {
            Assert.assertTrue(deptCmb.isDisplayed());
        }

        Assert.assertFalse(deptCmb.isSelected());
        Assert.assertTrue(deptCmb.isEnabled());

        String text = deptCmb.getText();

        if (webDriver instanceof InternetExplorerDriver) {
            Assert.assertEquals(text, "All departments Categories Brand");
        } else {
            Assert.assertEquals(text, "All departments\nCategories\nBrand");
        }
    }

    @Test
    public void testSearchSpree() throws InterruptedException {
        HomePage homePage = Pages.openHomePage(webDriver);

        ProductsPage productsPage = homePage.search("Bag");
        productsPage.validateTitle();

        productsPage.validateSearchText("Bag");
        productsPage.clearSearch();
        productsPage.validateSearchText("");

        homePage = productsPage.clickLogo();
    }

    @Test
    public void testA() throws Exception {
        Assert.assertTrue(true);
    }

    @Test
    public void testB() {
        Assert.assertEquals(1, 1);
    }

    @Test
    public void testGetGoogleUrl() {
        webDriver.get("http://google.com");

        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Google");

        String url = webDriver.getCurrentUrl();
        String msg = "Found " + url;
        Assert.assertTrue(url.startsWith("https://www.google.com"), msg);
    }

    @Test
    public void testNoComments() {
        webDriver.get("http://www.example.com");
        String source = webDriver.getPageSource();

        Assert.assertFalse(source.contains("<!--"), "Found " + source);
    }

    @Test
    public void testGetGoogleUrl2() {
        Response response = ClientBuilder.newClient().target("http://google.com").request().get();
        Assert.assertEquals(response.getStatus(), 200);
    }

    @Test
    public void testHomePage() {
//        webDriver.get("https://spreecommerce-demo.herokuapp.com");
//        String title = webDriver.getTitle();
//        Assert.assertEquals(title, "Spree Demo Site");

        HomePage homePage = Pages.openHomePage(webDriver);
        homePage.validateTitle();
//        homePage.validateUrl();
    }

    @Test
    public void testCartPage() {
        CartPage cartPage = Pages.openCartPage(webDriver);
        cartPage.validateTitle();
        // cartPage.validateUrl();
    }

    @Test
    public void testProductPage() {
        ProductPage productPage = Pages.openProductPage(webDriver, "Spree Tote");
        productPage.validateTitle();
    }

    @Test
    public void testBackAndForth() {
        webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-bag");
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Bag - Spree Demo Site");

        webDriver.navigate().to("https://spreecommerce-demo.herokuapp.com/products/spree-tote");
        title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");

        webDriver.navigate().back();
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");

        webDriver.navigate().forward();
        Assert.assertEquals(webDriver.getTitle(), "Spree Tote - Spree Demo Site");
    }

    @Test
    public void testRefresh() throws Exception {
        webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-bag");
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");

        webDriver.navigate().refresh();
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");
    }

    @Test
    public void testCapabilities() {

        Capabilities capabilities = webDriver.getCapabilities();

        String name = capabilities.getBrowserName();

        if (name.equals("chrome")) {
            Assert.assertEquals(name, "chrome");

//            String version = capabilities.getVersion();
//            Assert.assertEquals(version, "54.0.2840.71");
//
//            String os = capabilities.getPlatform().name();
//            Assert.assertEquals(os, "XP");

        } else if (webDriver instanceof FirefoxDriver) {
            Assert.assertEquals(name, "firefox");

//            String version = capabilities.getVersion();
//            Assert.assertEquals(version, "47.0.2");
//
//            String os = capabilities.getPlatform().name();
//            Assert.assertEquals(os, "WINDOWS");

        } else if (driverType == DriverType.IE) {
            Assert.assertEquals(name, "internet explorer");

            String version = capabilities.getVersion();
            Assert.assertEquals(version, "11");

            String os = capabilities.getPlatform().name();
            Assert.assertEquals(os, "WINDOWS");
        }

        Assert.assertTrue(capabilities.isJavascriptEnabled());
    }

    @AfterMethod
    public void afterMethod(ITestResult results) throws Exception {
        try {
            if (results.isSuccess() == false) {

                File screenShotDir = new File("/tmp/test-failures");
                screenShotDir.mkdirs();

                File tempFile = webDriver.getScreenshotAs(OutputType.FILE);

                String className = getClass().getSimpleName();
                String methodName = results.getMethod().getMethodName();
                String fileName = String.format("%s.%s [%s].png", className, methodName, driverType);

                FileUtils.copyFile(tempFile, new File(screenShotDir, fileName));
            }
        } catch (RuntimeException | IOException e) {
            e.printStackTrace();

        } finally {
            webDriver.quit();
        }
    }

    @Factory()
    public static Object[] testFactory() {
        List<ShoppingSpreeTests> data = new ArrayList<>();
        // data.add(new ShoppingSpreeTests(DriverType.IE));
        // data.add(new ShoppingSpreeTests(DriverType.Safari));
         // data.add(new ShoppingSpreeTests(DriverType.Firefox));
        // data.add(new ShoppingSpreeTests(DriverType.Chrome));
         data.add(new ShoppingSpreeTests(DriverType.Grid));
        return data.toArray();
    }
}
