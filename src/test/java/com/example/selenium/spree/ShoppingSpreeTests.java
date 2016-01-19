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

  private WebDriver browser;

  @Before
  public void beforeMethod() throws Exception {
//    HtmlUnitDriver webDriver = new HtmlUnitDriver();
//    webDriver.setJavascriptEnabled(true);
//    FirefoxDriver webDriver = new FirefoxDriver();

//    String path = System.getenv("webdriver.chrome.driver");
//    System.setProperty("webdriver.chrome.driver", path);
//    ChromeDriver webDriver = new ChromeDriver();
    
    String path = System.getenv("webdriver.ie.driver");
    System.setProperty("webdriver.ie.driver", path);
    InternetExplorerDriver webDriver = new InternetExplorerDriver();

    browser = webDriver;
  }

  @Test
  public void testHomePageTitle() {
    browser.get("http://spree.newcircle.com");
    Assert.assertEquals("Spree Demo Site", browser.getTitle());
  }

  @After
  public void afterMethod() {
    browser.quit();
  }
}
