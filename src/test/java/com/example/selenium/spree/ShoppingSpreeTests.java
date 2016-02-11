package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;

import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter;

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
    LogbackUtils.setLevel(StrictErrorReporter.class, Level.OFF);
  }

  private WebDriver webDriver;
  private final WebDriverFactory wdf;
  
  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
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
    webDriver.get("http://spree.newcircle.com");
    Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
  }

  @After
  public void afterMethod() {
    webDriver.quit();
  }
  
  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList();
    data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    // data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    return data;
  }
}
