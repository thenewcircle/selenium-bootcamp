package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
  }

  private WebDriver browser;

  @Before
  public void beforeMethod() throws Exception {
    browser = new HtmlUnitDriver();
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
