package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.example.selenium.LogbackUtils;
import com.example.selenium.spree.utils.ChromeDriverFactory;
import com.example.selenium.spree.utils.FirefoxDriverFactory;
import com.example.selenium.spree.utils.HtmlUnitDriverFactory;
import com.example.selenium.spree.utils.InternetExplorerDriverFactory;
import com.example.selenium.spree.utils.RichWebDriver;
import com.example.selenium.spree.utils.ScreenshotRule;
import com.example.selenium.spree.utils.SeleniumTest;
import com.example.selenium.spree.utils.WebDriverFactory;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import com.gargoylesoftware.htmlunit.javascript.StrictErrorReporter;

import ch.qos.logback.classic.Level;

@RunWith(Parameterized.class)
public class SeleniumSmokeTest implements SeleniumTest {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
    LogbackUtils.setLevel(StrictErrorReporter.class, Level.OFF);
  }

  private RichWebDriver browser;
  private final WebDriverFactory wdf;
  
  @Rule
  public final ScreenshotRule screenshotRule = 
    new ScreenshotRule(this, "c:\\tmp\\test-failures");
  
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
  
  @Test
  public void testBackButton() {
    browser.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", browser.getTitle());

    browser.navigate().to("http://spree.newcircle.com/products/spree-tote");
    Assert.assertEquals("Spree Tote - Spree Demo Site", browser.getTitle());

    browser.navigate().back();  
    Assert.assertEquals("Spree Bag - Spree Demo Site", browser.getTitle());

    browser.navigate().forward();
    Assert.assertEquals("Spree Tote - Spree Demo Site", browser.getTitle());
  }
  
  @Test
  public void testRefresh() throws Exception {
	  browser.get("http://spree.newcircle.com/products/spree-bag");
	  Assert.assertEquals("Spree Bag - Spree Demo Site", browser.getTitle());
	  
	  browser.navigate().refresh();
	  Assert.assertEquals("Spree Bag - Spree Demo Site", browser.getTitle());
  }
  
  @Test
  public void testGetCurrentUrl() throws Exception {
	  Assume.assumeFalse(browser.isHtmlUnit());
	  
	  browser.get("http://google.com");
	  Assert.assertEquals("Google", browser.getTitle());
	  
	  String url = browser.getCurrentUrl();
	  String msg = "I got instead " + url;
	  Assert.assertTrue(msg, url.startsWith("https://www.google.com"));
  }
  
  @Test
  public void testGetPageSource() throws Exception {
	  browser.get("http://example.com");
	  String src = browser.getPageSource();
	  
	  String tag = "<!DOCTYPE html>";
	  if (browser.isHtmlUnit()) {
		  tag = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	  } else if (browser.isIE()) {
		  tag = "<html>";
	  }
	  
	  String start = src.substring(0, tag.length());
	  Assert.assertEquals(tag, start);
	  Assert.assertTrue(false);
  }
  
//  @After
//  public void afterMethod() {
//    browser.quit();
//  }
  
  @Parameterized.Parameters(name = "{0}")
  public static Iterable<Object[]> createTestParameters() {
	  List<Object[]> data = new ArrayList();
	  data.add(new Object[]{ "Firefox",  new FirefoxDriverFactory()});
	  data.add(new Object[]{ "Chrome",   new ChromeDriverFactory()});
	  data.add(new Object[]{ "IE",   new InternetExplorerDriverFactory()});
	  data.add(new Object[]{ "HtmlUnit", new HtmlUnitDriverFactory()});
	  return data;
  }


  @Override
  public RichWebDriver getRichWebDriver() {
    return browser;
  }
}
