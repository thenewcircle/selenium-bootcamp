package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.example.selenium.LogbackUtils;
import com.example.selenium.spree.utils.ChromeDriverFactory;
import com.example.selenium.spree.utils.FirefoxDriverFactory;
import com.example.selenium.spree.utils.HtmlUnitDriverFactory;
import com.example.selenium.spree.utils.InternetExplorerDriverFactory;
import com.example.selenium.spree.utils.RichWebDriver;
import com.example.selenium.spree.utils.WebDriverFactory;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class SeleniumSmokeTest {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
  }

  private RichWebDriver browser;
  private final WebDriverFactory wdf;
  
  public SeleniumSmokeTest(String name, WebDriverFactory wdf) {
	  this.wdf = wdf;
  }

  
  @Before
  public void beforeMethod() throws Exception {
	  browser = wdf.create();
  }

  @Test
  public void testHomePageTitle() {
    browser.get("http://spree.newcircle.com/products");
    Assert.assertEquals("Spree Demo Site", browser.getTitle());
  }

  @After
  public void afterMethod() {
    browser.quit();
  }
  
  @Parameterized.Parameters(name = "{0}")
  public static Iterable<Object[]> createTestParameters() {
	  List<Object[]> data = new ArrayList();
	  data.add(new Object[]{ "Firefox",  new FirefoxDriverFactory()});
	  data.add(new Object[]{ "Chrome",   new ChromeDriverFactory()});
	  data.add(new Object[]{ "IE",   new InternetExplorerDriverFactory()});
	  data.add(new Object[]{ "HtmlUnit", new HtmlUnitDriverFactory()});
	  return data;
  }
}
