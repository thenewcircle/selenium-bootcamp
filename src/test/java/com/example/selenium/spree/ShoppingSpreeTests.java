package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
  }

  private RemoteWebDriver webDriver;
  
  private WebDriverFactory wdf;

  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }
  
  @Before
  public void beforeMethod() throws Exception {
    webDriver = wdf.create();
  }

  @Test
  public void testHomePageTitle() {
    webDriver.get("http://spree.newcircle.com");
    Assert.assertEquals("Spree Demo Site", 
                        webDriver.getTitle());
    
    Assert.assertEquals("http://spree.newcircle.com/", 
                        webDriver.getCurrentUrl());
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
    data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    return data;
  }
}
