package com.example.selenium.spree;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter;

import ch.qos.logback.classic.Level;

@Listeners(SeleniumUtils.class)

@Test
public class ShoppingSpreeTests /*implements SeleniumTest*/ {

  static {
    LogbackUtils.initLogback(Level.INFO);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
    LogbackUtils.setLevel(StrictErrorReporter.class, Level.OFF);
  }

  private RemoteWebDriver webDriver;
  
  @BeforeMethod
  public void beforeMethod() throws Exception {
    webDriver = new FirefoxDriver();
  }

  @Test(enabled=false)
  public void testHomePageTitle() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    homePage.validateTitle();
  }

  public void testProductPageTitle() {
    ProductPage productPage = new ProductPage(webDriver);
    productPage.open();
    productPage.validateTitle();
  }
  
  @Test(dependsOnMethods="testProductPageTitle")
  public void testCartPageTitle() {
    CartPage cartPage = new CartPage(webDriver);
    cartPage.open();
    cartPage.validateTitle();
  }
  
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
  
  public void testRefresh() {
    webDriver.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().refresh();
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());
  }

  public void testGetGoogleUrl() {
    webDriver.get("http://google.com");;
    Assert.assertEquals("Google", webDriver.getTitle());

    String title = webDriver.getCurrentUrl();
    String msg = "Found: " + title;
    Assert.assertTrue(title.startsWith("https://www.google.com/?"), msg);
  }
  
  public void testIeComments() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    homePage.validateIeComments();
  }

  public void testCapabilities() {

    Assert.assertEquals(true, webDriver.getCapabilities().isJavascriptEnabled());

    if (webDriver instanceof FirefoxDriver) {
//      Assert.assertEquals("firefox", webDriver.getCapabilities().getBrowserName());
//      Assert.assertEquals("44.0.2", webDriver.getCapabilities().getVersion());
//      Assert.assertEquals("WINDOWS", webDriver.getCapabilities().getPlatform());

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
  
  public void testDepartmentCombo() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    WebElement deptCmb = homePage.getDepartmentCmb();

    Assert.assertEquals("Taxon", deptCmb.getAttribute("aria-label"));

    if (webDriver instanceof FirefoxDriver) {
      Assert.assertEquals("18px", deptCmb.getCssValue("height"));
      
    } else if (webDriver instanceof InternetExplorerDriver) {
      Assert.assertEquals("15.79px", deptCmb.getCssValue("height"));
      
    } else if (webDriver instanceof ChromeDriver) {
      Assert.assertEquals("17px", deptCmb.getCssValue("height"));
    }
    
    Point loc = deptCmb.getLocation();
    Assert.assertTrue(loc.getX() > 100, "Found: "+loc);
    Assert.assertTrue(loc.getY() < 200, "Found: "+loc);

    Dimension size = deptCmb.getSize();
    Assert.assertTrue(size.getWidth() > 100 && size.getWidth() < 200, "Found: "+size);
    Assert.assertTrue(size.getHeight() > 15 && size.getHeight() < 25, "Found: "+size);
        
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
  // @Test(dataProvider = "webDrivers")
  public void testSearchSpree(/*WebDriverFactory factory*/) throws Exception {
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
    
    Assert.assertTrue(false);
  }
  
  @AfterMethod
  public void afterMethod(ITestResult results) {
    // String path = System.getenv("tests.selenium.screenshots");
    String path = "c:\\tmp\\test-failures";
    
    File screenShotDir = new File(path);
    String name = String.format("%s.%s(%s)", getClass().getName(), results.getMethod().getMethodName(), Arrays.asList(results.getParameters()));
    SeleniumUtils.captureScreenshot(webDriver, screenShotDir, name);

    webDriver.quit();
  }
  
  @DataProvider(name = "webDrivers")
  public static Iterator<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList();
//  data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
//    data.add(new Object[]{ new FirefoxDriverFactory() });
//    data.add(new Object[]{ new InternetExplorerDriverFactory() });
    data.add(new Object[]{ new ChromeDriverFactory()});
    return data.iterator();
  }

}
