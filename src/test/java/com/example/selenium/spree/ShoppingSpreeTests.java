package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests implements SeleniumTest {

  static {
    LogbackUtils.initLogback(Level.WARN);
  }

  private WebDriverFactory wdf;
  private RemoteWebDriver webDriver;

  @Rule
  public final ScreenshotRule screenshotRule = 
    new ScreenshotRule(this, "\\tmp\\test-failures");

  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }
  
  @Before
  public void beforeMethod() throws Exception {
    webDriver = wdf.create();
    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
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
    if (webDriver instanceof SafariDriver) {
      return;
    }
    
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
    Assert.assertTrue(msg, actual.startsWith(expected));
  }
  
  @Test
  public void testDepartmentsCombo() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    WebElement deptCmb = homePage.getDepartmentCmb();

    String attr = deptCmb.getAttribute("aria-label");
    Assert.assertEquals("Taxon", attr);
    
    String height = deptCmb.getCssValue("height");
    if (homePage.isThisFirefox()) {
      if (homePage.isMac()) {
        Assert.assertEquals("19.3333px", height);
      } else {
        Assert.assertEquals("18px", height);
      }
    } else if (homePage.isThisIe()) {
      Assert.assertEquals("15.79px", height);

    } else {
      if (homePage.isMac()) {
        Assert.assertEquals("18px", height);
      } else {
        Assert.assertEquals("17px", height);
      }
    }
    
    Point location = deptCmb.getLocation();
    String msg = "location = "  +location;
    Assert.assertTrue(msg, location.getX() > 100);
    Assert.assertTrue(msg, location.getY() < 200);

    Dimension size = deptCmb.getSize();
    msg = "size = "  + size;
    Assert.assertTrue(msg, size.getWidth() > 100 && size.getWidth() < 200);
    Assert.assertTrue(msg, size.getHeight() > 15 && size.getHeight() < 25);
  
    String tagName = deptCmb.getTagName();
    Assert.assertEquals("select", tagName);

    Assert.assertTrue(deptCmb.isDisplayed());
    Assert.assertFalse(deptCmb.isSelected());
    Assert.assertTrue(deptCmb.isEnabled());

    String text = deptCmb.getText();
    if (homePage.isThisIe()) {
      Assert.assertEquals("All departments Categories Brand", text);
    } else {
      Assert.assertEquals("All departments\nCategories\nBrand", text);
    }
  }
  
  @Test
  public void testSearchSpree() throws Exception {
    if (webDriver instanceof SafariDriver) {
      return;
    }

    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    
    ProductsPage productsPage = homePage.search("Bag");
    productsPage.validateUrl();
    productsPage.validateTitle();
    productsPage.clearSearch();

    homePage = productsPage.clickLogo();
    
    // Thread.sleep(10*1000);
    WebDriverWait wait = new WebDriverWait(webDriver, 5, 250);
    wait.until(ExpectedConditions.urlContains("http://spree.newcircle.com/"));
    
    // homePage.validateUrl();
  }
  
//@After
//public void afterMethod() {
//  webDriver.quit();
//}

  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList<>();
    data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    
    String OS = System.getProperty("os.name");
    if (OS.startsWith("Windows")) {
      data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    } else {
      data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    }

    return data;
  }
  
  public RemoteWebDriver getRemoteWebDriver() {
    return webDriver;
  }
}
