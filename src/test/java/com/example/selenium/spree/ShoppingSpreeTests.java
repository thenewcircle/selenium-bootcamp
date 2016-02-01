package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
  }

  private WebDriver webDriver;

  @Before
  public void beforeMethod() throws Exception {
    // HtmlUnitDriver wd= new HtmlUnitDriver();
    // wd.setJavascriptEnabled(true);
    FirefoxDriver wd = new FirefoxDriver();

    this.webDriver = wd;
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
