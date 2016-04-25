package com.example.selenium.spree;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import ch.qos.logback.classic.Level;

public class ShoppingSpreeTests {

  static {
    LogbackUtils.initLogback(Level.WARN);
  }

  private WebDriver webDriver;

  @Before
  public void beforeMethod() throws Exception {
    System.setProperty("webdriver.chrome.driver", 
                         "C:\\tools\\selenium\\chromedriver.exe");
    webDriver = new ChromeDriver();
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
