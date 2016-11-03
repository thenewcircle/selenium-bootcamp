package com.example.selenium.spree;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.testng.Assert;
import org.testng.ITest;
import org.testng.annotations.*;
import ch.qos.logback.classic.Level;
import com.example.selenium.spree.support.LogbackUtils;

import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;
import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
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

        Assert.assertTrue(deptCmb.isDisplayed());
        Assert.assertFalse(deptCmb.isSelected());
        Assert.assertTrue(deptCmb.isEnabled());

        String text = deptCmb.getText();
        Assert.assertEquals(text, "All departments\nCategories\nBrand");
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
        homePage.validateUrl();
    }

    @Test
    public void testCartPage() {
        CartPage cartPage = Pages.openCartPage(webDriver);
        cartPage.validateTitle();
        cartPage.validateUrl();
    }

    @Test
    public void testProductPage() {
        ProductPage productPage = Pages.openProductPage(webDriver);
        productPage.validateTitle();
        productPage.validateUrl();
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
    public void testRefresh() {
        webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-bag");
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");

        webDriver.navigate().refresh();
        Assert.assertEquals(webDriver.getTitle(), "Spree Bag - Spree Demo Site");
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
        // data.add(new ShoppingSpreeTests(DriverType.Firefox));
        data.add(new ShoppingSpreeTests(DriverType.Chrome));
        return data.toArray();
    }
}
