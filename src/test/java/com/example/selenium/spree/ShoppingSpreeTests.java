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

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
  }

  private WebDriverFactory wdf;
  private RemoteWebDriver webDriver;

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
    CartPage cartPage = new CartPage(webDriver);
    cartPage.open();
    cartPage.validateTitle();
    
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    homePage.validateTitle();
    
    webDriver.navigate().back();
    cartPage.validateTitle();
    
    webDriver.navigate().forward();
    homePage.validateTitle();

    webDriver.navigate().refresh();
    homePage.validateTitle();
  }
  
  @Test
  public void testGetGoogleUrl() {
    webDriver.get("http://google.com");
    String actual = webDriver.getCurrentUrl();
    String expected = "https://www.google.com/?";
    String msg = "Found: " + actual;
    System.out.println(msg);
    Assert.assertTrue(msg, actual.startsWith(expected));
  }
  
  @After
  public void afterMethod() {
    webDriver.quit();
  }

  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList<>();
    data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    // data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    // data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    return data;
  }
}
