package com.example.selenium.spree;

import org.openqa.selenium.Capabilities;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingSpreeTests extends BaseTests {
    public ShoppingSpreeTests(DriverType driverType) {
        super(driverType);
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

    @Test
    public void testCartPage() {
        webDriver.navigate().to("https://selenium.jacobparr.com/cart");
        Assert.assertEquals(webDriver.getTitle(), "Shopping Cart - Spree Demo Site");
        Assert.assertEquals(webDriver.getCurrentUrl(), "https://selenium.jacobparr.com/cart");
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
}
