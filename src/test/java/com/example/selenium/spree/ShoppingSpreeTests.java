package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
  }

  private RemoteWebDriver webDriver;
  private final WebDriverFactory wdf;

  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }

  @Before
  public void beforeMethod() throws Exception {
    // HtmlUnitDriver wd= new HtmlUnitDriver();
    // wd.setJavascriptEnabled(true);
    // FirefoxDriver wd = new FirefoxDriver();

    // String path = System.getenv("webdriver.chrome.driver");
    // System.setProperty("webdriver.chrome.driver", path);
    // ChromeDriver wd = new ChromeDriver();

    // String path = System.getenv("webdriver.ie.driver");
    // System.setProperty("webdriver.ie.driver", path);
    // InternetExplorerDriver wd = new InternetExplorerDriver();

    this.webDriver = wdf.create();
  }

  @Test
  public void testHomePageTitle() {
    // webDriver.get("http://spree.newcircle.com");
    // Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
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

  @After
  public void afterMethod() {
    webDriver.quit();
  }

  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList();
    // data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    // data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    //data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    return data;
  }
}
