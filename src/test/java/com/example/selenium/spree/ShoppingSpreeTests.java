package com.example.selenium.spree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter;

import ch.qos.logback.classic.Level;

public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
    LogbackUtils.setLevel(StrictErrorReporter.class, Level.OFF);
  }

  private WebDriver webDriver;

  @Before
  public void beforeMethod() throws Exception {
//    HtmlUnitDriver wd = new HtmlUnitDriver();
//    wd.setJavascriptEnabled(true);
//    FirefoxDriver wd = new FirefoxDriver();
    
//    System.setProperty("webdriver.chrome.driver", "C:\\tools\\selenium\\chromedriver.exe");
//    ChromeDriver wd = new ChromeDriver();

    System.setProperty("webdriver.ie.driver", "C:\\tools\\selenium\\IEDriverServer32.exe");
    InternetExplorerDriver wd = new InternetExplorerDriver();
    
    webDriver = wd;
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
}
