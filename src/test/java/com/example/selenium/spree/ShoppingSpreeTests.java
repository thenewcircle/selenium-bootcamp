package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests implements SeleniumTest {
  static { LogbackUtils.initLogback(Level.WARN); }
  private WebDriverFactory wdf;
  private RemoteWebDriver webDriver;

  @Rule
  public final ScreenshotRule screenshotRule = 
    new ScreenshotRule(this, "c:\\tmp\\test-failures");
  
  public RemoteWebDriver getWebDriver() {
    return webDriver;
  }
  
  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }
  
  @Before
  public void beforeMethod() throws Exception {
    webDriver = wdf.create();
    webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

    webDriver.get("http://spree.newcircle.com");
    webDriver.manage().deleteAllCookies();
  }

  @Test
  public void testHomePageTitle() {
    HomePage homePage = Pages.openHomePage(webDriver);
    homePage.validateUrl();
    homePage.validateTitle();
  }

  @Test
  public void testCartPageTitle() {
    CartPage cartPage = Pages.openCartPage(webDriver);
    cartPage.validateUrl();
    cartPage.validateTitle();
  }
  
  @Test
  public void testGoogle() {
    webDriver.get("http://google.com");
    Assert.assertEquals("Google", 
                        webDriver.getTitle());
    
    String msg = "Found: " + webDriver.getCurrentUrl();
    Assert.assertTrue(msg, webDriver.getCurrentUrl().contains("www.google.com"));
  }

  @Test
  public void testBackAndForth() {
    Assume.assumeFalse(webDriver instanceof SafariDriver);
    
    webDriver.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());
    Assert.assertEquals("http://spree.newcircle.com/products/spree-bag", webDriver.getCurrentUrl());
    
    webDriver.navigate().to("http://spree.newcircle.com/products/spree-tote");
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
    Assert.assertEquals("http://spree.newcircle.com/products/spree-tote", webDriver.getCurrentUrl());

    webDriver.navigate().back();
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());
    Assert.assertEquals("http://spree.newcircle.com/products/spree-bag", webDriver.getCurrentUrl());

    webDriver.navigate().forward();
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
    Assert.assertEquals("http://spree.newcircle.com/products/spree-tote", webDriver.getCurrentUrl());

    webDriver.navigate().refresh();
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
    Assert.assertEquals("http://spree.newcircle.com/products/spree-tote", webDriver.getCurrentUrl());
  }
  
  @Test
  public void testCapabilities() {

    //Assert.assertEquals(true, webDriver.getCapabilities().isJavascriptEnabled());
    Assert.assertTrue(webDriver.getCapabilities().isJavascriptEnabled());

    String name = webDriver.getCapabilities().getBrowserName();
    
    if ("firefox".equals(name)) {
      Assert.assertEquals("47.0.1", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", webDriver.getCapabilities().getPlatform().name());

    } else if ("chrome".equals(name)) {
      Assert.assertEquals("chrome", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("51.0.2704.103", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("XP", webDriver.getCapabilities().getPlatform().name());

    } else if ("internet explorer".equals(name)) {
      Assert.assertEquals("11", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", webDriver.getCapabilities().getPlatform().name());

    } else {
      throw new UnsupportedOperationException();
    }
  }
  
  
  
  @Test
  public void testDepartmentsCombo() {
    HomePage homePage = Pages.openHomePage(webDriver);
    WebElement deptCmb = homePage.getDepartmentCmb();

    //  ... that getAttribute("aria-label") is Taxon.
    String attrValue = deptCmb.getAttribute("aria-label");
    Assert.assertEquals("Taxon", attrValue);

    
    //  ...that getCssValue("height") is 26px.
    if (webDriver instanceof FirefoxDriver) {
      String cssValue = deptCmb.getCssValue("height");
      Assert.assertEquals("20px", cssValue);
      
    } else if (webDriver instanceof InternetExplorerDriver) {
      String cssValue = deptCmb.getCssValue("height");
      Assert.assertEquals("15.79px", cssValue);
    } else {
      
      String cssValue = deptCmb.getCssValue("height");
      Assert.assertEquals("17px", cssValue);
    }
    
    
    //  ...that getLocation().getX() is greater than 100.
    //  ...that getLocation().getY() is less than 200.
    Point location = deptCmb.getLocation();
    String msg = "Found: " + location;
    Assert.assertTrue(msg, location.x > 100);
    Assert.assertTrue(msg, location.y < 200);
    
    //  ...that getSize().getWidth() is between 100 & 120.
    //  ...that getSize().getHeight() is between 15 & 25.
    Dimension size = deptCmb.getSize();
    msg = "Found: " + size;
    Assert.assertTrue(msg, size.width > 100 && size.width < 120);
    Assert.assertTrue(msg, size.height > 15 && size.height < 25);

    //  ...that getTagName() is select.
    String tag = deptCmb.getTagName();
    Assert.assertEquals("select", tag);
    
    //  ...that isDisplayed() is true.
    boolean displayed = deptCmb.isDisplayed();
    Assert.assertTrue(displayed);
    
    //  ...that isSelected() is false.
    boolean selected = deptCmb.isSelected();
    Assert.assertFalse(selected);
    
    //  ...that isEnabled() is true.
    boolean enabled = deptCmb.isEnabled();
    Assert.assertTrue(enabled);
    
    //  ...that getText() is...
    String bn = webDriver.getCapabilities().getBrowserName();
    
    if ("internet explorer".equals(bn)) {
      String text = deptCmb.getText();
      String expected = "All departments Categories Brand";
      Assert.assertEquals(expected, text);
      
    } else {
      String text = deptCmb.getText();
      String expected = "All departments\nCategories\nBrand";
      Assert.assertEquals(expected, text);
    }
  }
  
  @Test
  public void testSearchSpree() throws Exception {
    HomePage homePage = Pages.openHomePage(webDriver);

    ProductsPage productsPage = homePage.search("Bag");
    productsPage.validateUrl();
    productsPage.clearSearch();    
    
    homePage = productsPage.clickLogo();
    homePage.validateTitle();
    // homePage.validateUrl();
  }
  
  @Test
  public void testShoppingSpree() throws Exception {
    HomePage homePage = Pages.openHomePage(webDriver);
    
    ProductsPage productsPage = homePage.search("Mug");

    ProductPage productPage = productsPage.clickProductLnk("Spree Mug");
    productPage.validateImageSrc("http://spree.newcircle.com/spree/products/45/product/spree_mug.jpeg?1420925090");
    productPage.clickThumbnail(1);
    productPage.validateImageSrc("http://spree.newcircle.com/spree/products/46/product/spree_mug_back.jpeg?1420925091");
    productPage.setQuantity(3);
    productPage.validateCartLink(0, null);
    
    CartPage cartPage = productPage.clickAddToCart();
    cartPage.validateCartLink(3, "41.97");
    productsPage = cartPage.clickContinueShopping();
  }
  
//  @After
//  public void afterMethod() {
//    webDriver.quit();
//  }
  
  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList<>();
//    data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
//    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
//     data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
//    data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    data.add(new Object[]{ new GridDriverFactory(), "Grid" });
    return data;
  }
}
