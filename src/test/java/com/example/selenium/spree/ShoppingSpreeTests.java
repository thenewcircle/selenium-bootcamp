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
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import com.example.selenium.LogbackUtils;
import com.example.selenium.spree.factories.ChromeDriverFactory;
import com.example.selenium.spree.factories.FirefoxDriverFactory;
import com.example.selenium.spree.factories.InternetExplorerDriverFactory;
import com.example.selenium.spree.factories.WebDriverFactory;
import com.example.selenium.spree.pages.CartPage;
import com.example.selenium.spree.pages.HomePage;
import com.example.selenium.spree.pages.ProductPage;
import com.example.selenium.spree.pages.ProductsPage;
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
    webDriver = wdf.create();
    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    webDriver.manage().window().maximize();
    webDriver.manage().window().setSize(new Dimension(1024, 768));

//    webDriver.get("http://spree.newcircle.com");
//    webDriver.manage().deleteAllCookies();
  }

  @Test
  public void testHomePageTitle() {
    HomePage homePage = HomePage.open(webDriver);
    homePage.validateTitle();
  }

  @Test
  public void testProductPageTitle() {
    ProductPage productPage = ProductPage.open(webDriver, "Spree Tote");
    productPage.validateTitle();
  }
  
  @Test
  public void testCartPageTitle() {
//    CartPage cartPage = new CartPage(webDriver);
//    cartPage.open();
//    cartPage.validateTitle();
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
    HomePage homePage = HomePage.open(webDriver);
    homePage.validateIeComments();
  }

  @Test
  public void testCapabilities() {

    Assert.assertEquals(true, webDriver.getCapabilities().isJavascriptEnabled());

    if (webDriver instanceof FirefoxDriver) {
      Assert.assertEquals("firefox", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("44.0.2", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", webDriver.getCapabilities().getPlatform().name());

    } else if (webDriver instanceof ChromeDriver) {
      Assert.assertEquals("chrome", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("48.0.2564.109", webDriver.getCapabilities().getVersion());
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
    HomePage homePage = HomePage.open(webDriver);
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
    HomePage homePage = HomePage.open(webDriver);
    
    ProductsPage productsPage = homePage.search("Bag");
    
    productsPage.validateTitle();
    productsPage.clearSearch();
    
    homePage = productsPage.clickLogo();
    // Thread.sleep(10*1000);

    // homePage.validateUrl();
    homePage.validateTitle();
  }
  
  @Test
  public void testShoppingSpree() throws Exception {
    HomePage homePage = HomePage.open(webDriver);

    ProductsPage productsPage = homePage.search("Mug");

    ProductPage productPage = productsPage.clickProductLnk("Spree Mug");
    productPage.validateImageSrc(
      "http://spree.newcircle.com/spree/products/45/product/spree_mug.jpeg?");

    productPage.clickThumbnail(1);
    productPage.validateImageSrc(
      "http://spree.newcircle.com/spree/products/46/product/spree_mug_back.jpeg?");
    productPage.setQuantity(3);
    productPage.validateCartLink(0, null);
    
    CartPage cartPage = productPage.clickAddToCart();
    cartPage.validateCartLink(3, "41.97");

    productsPage = cartPage.clickContinueShopping();
    // needs web driver wait in clickContinueShopping()
  }
  
  @Test
  public void testMouseOver() throws Exception {
    HomePage homePage = HomePage.open(webDriver);
    ProductsPage productsPage = homePage.search("Stein");

    ProductPage productPage = productsPage.clickProductLnk("Ruby on Rails Stein");
    productPage.validateImageSrc(
      "http://spree.newcircle.com/spree/products/31/product/ror_stein.jpeg?");

    productPage.mouseOverThumbnail(1);
 
    productPage.validateImageSrc(
      "http://spree.newcircle.com/spree/products/32/product/ror_stein_back.jpeg?");
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
