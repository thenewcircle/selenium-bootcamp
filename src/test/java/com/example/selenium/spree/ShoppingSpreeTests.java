package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.example.selenium.LogbackUtils;
//import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
//    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
  }

  private RemoteWebDriver webDriver;
  private final WebDriverFactory wdf;
  
  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }

  @Before
  public void beforeMethod() throws Exception {
    webDriver = wdf.create();
  }

  @Test
  public void testHomePageTitle() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    homePage.validateTitle();
  }

  @Test
  public void testProductPageTitle() {
    ProductPage productPage = new ProductPage(webDriver);
    productPage.open();
    productPage.validateTitle();
  }
  
  @Test
  public void testCartPageTitle() {
    CartPage cartPage = new CartPage(webDriver);
    cartPage.open();
    cartPage.validateTitle();
  }
  
  @Test
  public void testBackAndForth() {
    webDriver.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().to("http://spree.newcircle.com/products/spree-tote");
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
  
    webDriver.navigate().back();
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().forward();
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().refresh();
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
  }
  
  @After
  public void afterMethod() {
    webDriver.quit();
  }
  
  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList();
    // data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    // data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    // data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    return data;
  }
}
