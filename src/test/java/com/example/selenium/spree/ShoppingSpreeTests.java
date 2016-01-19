package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter;

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests implements SeleniumTest {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
    LogbackUtils.setLevel(StrictErrorReporter.class, Level.OFF);
  }

  private RemoteWebDriver browser;
  private final WebDriverFactory wdf;

  @Rule
  public final ScreenshotRule screenshotRule = 
    new ScreenshotRule(this, "c:\\tmp\\test-failures");
  
  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }

  @Before
  public void beforeMethod() throws Exception {
    browser = wdf.create();
  }

  @Test
  public void testHomePageTitle() {
    // browser.get("http://spree.newcircle.com");
    // Assert.assertEquals("Spree Demo Site", browser.getTitle());
    HomePage homePage = new HomePage(browser);
    homePage.open();
    homePage.validateTitle();
  }

  @Test
  public void testBackAndForth() {
    browser.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", browser.getTitle());

    browser.get("http://spree.newcircle.com/products/spree-tote");
    Assert.assertEquals("Spree Tote - Spree Demo Site", browser.getTitle());

    browser.navigate().back();
    Assert.assertEquals("Spree Bag - Spree Demo Site", browser.getTitle());

    browser.navigate().forward();
    Assert.assertEquals("Spree Tote - Spree Demo Site", browser.getTitle());
  }

  @Test
  public void testGetGoogleUrl() {
    browser.get("http://google.com");
    String url = browser.getCurrentUrl();
    boolean value = url.startsWith("https://www.google.com/?");
    String msg = "Found " + url;
    Assert.assertTrue(msg, value);
  }
  
  @Test
  public void testIeComments() {
    HomePage homePage = new HomePage(browser);
    homePage.open();
    homePage.validateIeComments();
  }
  
  @Test
  public void testRefresh() {
    browser.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", browser.getTitle());

    browser.navigate().refresh();
    Assert.assertEquals("Spree Bag - Spree Demo Site", browser.getTitle());
  }
  
  @Test
  public void testProductPageTitle() {
    ProductPage productPage = new ProductPage(browser);
    productPage.open();
    productPage.validateTitle();
    
    Assert.assertTrue(false);
  }
  
  @Test
  public void testCartPageTitle() {
    CartPage cartPage = new CartPage(browser);
    cartPage.open();
    cartPage.validateTitle();
  }
  
  @Test
  public void testCapabilities() {

    Assert.assertEquals(true, browser.getCapabilities().isJavascriptEnabled());

    if (browser instanceof FirefoxDriver) {
      Assert.assertEquals("firefox", browser.getCapabilities().getBrowserName());
      Assert.assertEquals("43.0.4", browser.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", browser.getCapabilities().getPlatform().name());

    } else if (browser instanceof ChromeDriver) {
      Assert.assertEquals("chrome", browser.getCapabilities().getBrowserName());
      Assert.assertEquals("47.0.2526.111", browser.getCapabilities().getVersion());
      Assert.assertEquals("XP", browser.getCapabilities().getPlatform().name());

    } else if (browser instanceof InternetExplorerDriver) {
      Assert.assertEquals("internet explorer", browser.getCapabilities().getBrowserName());
      Assert.assertEquals("11", browser.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", browser.getCapabilities().getPlatform().name());

    } else {
      throw new UnsupportedOperationException();
    }
  }
  
//  @After
//  public void afterMethod() {
//    browser.quit();
//  }
  
  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList();
//    data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
//    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    // data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    return data;
  }

  @Override
  public RemoteWebDriver getRemoteWebDriver() {
    return browser;
  }
}
