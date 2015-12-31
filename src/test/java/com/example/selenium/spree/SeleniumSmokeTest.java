package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.concurrent.TimeUnit;

public class SeleniumSmokeTest {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
  }

  private WebDriver wd;

  @Before
  public void setUp() throws Exception {
    wd = new HtmlUnitDriver();
    wd.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
  }

  @Test
  public void testVerifyJerseyPrice() {
    wd.get("http://spree.newcircle.com/products");
    Assert.assertEquals("Spree Demo Site", wd.getTitle());
    By by = By.cssSelector("#product_6 .price")).getText().contentEquals("$19.99");
    Assert.assertTrue(wd.findElement(by);
  }

  @After
  public void tearDown() {
    wd.quit();
  }
}
