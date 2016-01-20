package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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
    browser.manage().window().maximize();
//    browser.manage().window().setSize(new Dimension(800, 600));
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
  public void testDepartmentsCombo() throws Exception {
    HomePage homePage = new HomePage(browser);
    homePage.open();
    WebElement deptCmb = homePage.getDepartmentCmb();

    String value = deptCmb.getAttribute("aria-label");
    Assert.assertEquals("Taxon", value);

    value = deptCmb.getCssValue("background-color");
    Assert.assertEquals("rgba(255, 255, 255, 1)", value);

    Point location = deptCmb.getLocation();
    String msg = "Location " + location;
    Assert.assertTrue(msg, location.getX() > 300);
    Assert.assertTrue(msg, location.getY() < 200);
    
    Dimension size = deptCmb.getSize();
    msg = "Size " + size;
    Assert.assertTrue(msg, size.getWidth() > 100 &&
                           size.getWidth() < 120);
    
    Assert.assertTrue(msg, size.getHeight() > 15 &&
                           size.getHeight() < 25);
    
    Assert.assertEquals("select", deptCmb.getTagName());

    Assert.assertTrue(deptCmb.isDisplayed());
    Assert.assertFalse(deptCmb.isSelected());
    Assert.assertTrue(deptCmb.isEnabled());
    
    String text = deptCmb.getText();
    deptCmb.click();
    deptCmb.clear();
    deptCmb.submit();

    if (browser instanceof InternetExplorerDriver) {
      Assert.assertEquals("All departments Categories Brand", text);
    } else {
      Assert.assertEquals("All departments\nCategories\nBrand", text);
    }
    

    List<WebElement> options = deptCmb.findElements(By.tagName("option"));
    Assert.assertEquals(3, options.size());
    Assert.assertEquals("All departments", options.get(0).getText());
    Assert.assertEquals("Categories", options.get(1).getText());
    Assert.assertEquals("Brand", options.get(2).getText());
  }
  
  @Test
  public void testSearchSpree() throws Exception {
    HomePage homePage = new HomePage(browser);
    homePage.open();
    
    ProductsPage productsPage = homePage.search("Bag");
    productsPage.validateUrl();
    productsPage.validateTitle();
    productsPage.clearSearch();

    homePage = productsPage.clickLogo();

    // homePage.validateUrl();
  }
  
  @Test
  public void testProductPageTitle() {
    ProductPage productPage = new ProductPage(browser);
    productPage.open();
    productPage.validateTitle();
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
//     data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    return data;
  }

  @Override
  public RemoteWebDriver getRemoteWebDriver() {
    return browser;
  }
}
