package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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

  private RemoteWebDriver webDriver;
  private final WebDriverFactory wdf;
  
  @Rule
  public final ScreenshotRule screenshotRule = 
    new ScreenshotRule(this, "c:\\tmp\\test-failures");
  
  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }

  @Override
  public RemoteWebDriver getWebDriver() {
    return webDriver;
  }

  @Before
  public void beforeMethod() throws Exception {
//    HtmlUnitDriver wd = new HtmlUnitDriver();
//    wd.setJavascriptEnabled(true);
//    FirefoxDriver wd = new FirefoxDriver();
    

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

    webDriver.get("http://spree.newcircle.com/products/spree-tote");
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().back();
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().forward();
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
  }
  
  @Test
  public void testRefresh() {
    webDriver.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().refresh();
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());
  }

  @Test
  public void testGetGoogleUrl() {
    webDriver.get("http://google.com");;
    Assert.assertEquals("Google", webDriver.getTitle());

    String title = webDriver.getCurrentUrl();
    String msg = "Found: " + title;
    Assert.assertTrue(msg, title.startsWith("https://www.google.com/?"));
  }
  
  @Test
  public void testIeComments() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    homePage.validateIeComments();
  }

  @Test
  public void testCapabilities() {

    Assert.assertEquals(true, webDriver.getCapabilities().isJavascriptEnabled());

    if (webDriver instanceof FirefoxDriver) {
      Assert.assertEquals("firefox", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("43.0.4", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", webDriver.getCapabilities().getPlatform());

    } else if (webDriver instanceof ChromeDriver) {
      Assert.assertEquals("chrome", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("48.0.2564.103", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("XP", webDriver.getCapabilities().getPlatform().name());

    } else if (webDriver instanceof InternetExplorerDriver) {
      Assert.assertEquals("internet explorer", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("11", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", webDriver.getCapabilities().getPlatform().name());

    } else {
      throw new UnsupportedOperationException();
    }
  }
  
  @Test 
  public void testDepartmentCombo() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    WebElement deptCmb = homePage.getDepartmentCmb();

    Assert.assertEquals("Taxon", deptCmb.getAttribute("aria-label"));

    if (webDriver instanceof FirefoxDriver) {
      Assert.assertEquals("20px", deptCmb.getCssValue("height"));
      
    } else if (webDriver instanceof InternetExplorerDriver) {
      Assert.assertEquals("15.79px", deptCmb.getCssValue("height"));
      
    } else if (webDriver instanceof ChromeDriver) {
      Assert.assertEquals("17px", deptCmb.getCssValue("height"));
    }
    
    Point loc = deptCmb.getLocation();
    Assert.assertTrue("Found: "+loc, loc.getX() > 100);
    Assert.assertTrue("Found: "+loc, loc.getY() < 200);

    Dimension size = deptCmb.getSize();
    Assert.assertTrue("Found: "+size, size.getWidth() > 100 && size.getWidth() < 200);
    Assert.assertTrue("Found: "+size, size.getHeight() > 15 && size.getHeight() < 25);
        
    Assert.assertEquals("select", deptCmb.getTagName());

    Assert.assertTrue(deptCmb.isDisplayed());
    Assert.assertFalse(deptCmb.isSelected());
    Assert.assertTrue(deptCmb.isEnabled());
    
    // It's good enough to know that they existed.
    String text = deptCmb.getText();
    text = text.replace("\n", "").replace("\r", "").replace(" ", "");
    Assert.assertEquals("AlldepartmentsCategoriesBrand", text);
  }

  @Test
  public void testSearchSpree() throws Exception {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    
    ProductsPage productsPage = homePage.search("Bag");
    
    productsPage.validateUrl();
    productsPage.validateTitle();
    productsPage.clearSearch();
    
    homePage = productsPage.clickLogo();
    // Thread.sleep(10*1000);

    
    // homePage.validateUrl();
    homePage.validateTitle();
  }
  
//  @After
//  public void afterMethod() {
//    webDriver.quit();
//  }
  
  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList();
//  data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    return data;
  }
}
