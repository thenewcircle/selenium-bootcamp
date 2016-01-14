package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Assume;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.example.selenium.LogbackUtils;
import com.example.selenium.spree.utils.ChromeDriverFactory;
import com.example.selenium.spree.utils.FirefoxDriverFactory;
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
	  browser.manage().timeouts()
	    .implicitlyWait(10, TimeUnit.SECONDS);
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
  
  @Test
  public void testSearchSpree() throws Exception {
    browser.get("http://spree.newcircle.com");
    WebElement keywordsTF = browser.findElementById("keywords");
    String value = keywordsTF.getAttribute("value");
    Assert.assertEquals("", value);
    
    keywordsTF.sendKeys("Bag");
    value = keywordsTF.getAttribute("value");
    Assert.assertEquals("Bag", value);
    
    keywordsTF.submit();
  
    // we are on the second page
    
    String url = browser.getCurrentUrl();
    Assert.assertTrue(url.startsWith("http://spree.newcircle.com/products?"));

    keywordsTF = browser.findElementById("keywords");
    value = keywordsTF.getAttribute("value");
    Assert.assertEquals("Bag", value);

    keywordsTF.clear();
    value = keywordsTF.getAttribute("value");
    Assert.assertEquals("", value);

    browser.findElementById("logo").click();
    
    //Thread.sleep(1*1000);
    WebDriverWait wait = new WebDriverWait(browser, 10);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/"));
    
//    url = browser.getCurrentUrl();
//    Assert.assertEquals("http://spree.newcircle.com/", url);
  }
  
  @Test
  public void testGoogleSearch() {
    Assume.assumeFalse(browser.isHtmlUnit());

    browser.get("https://www.google.com/");
    WebElement searchTF = browser.findElementById("lst-ib");
    Assert.assertNotNull(searchTF);

    Assert.assertEquals("2048", searchTF.getAttribute("maxlength"));
    Assert.assertEquals("26px", searchTF.getCssValue("height"));

    Assert.assertTrue(searchTF.getLocation().getX() > 200);
    Assert.assertTrue(searchTF.getLocation().getY() > 100);

    Assert.assertTrue(searchTF.getSize().getWidth() > 400 &&
                      searchTF.getSize().getWidth() < 500);
    Assert.assertEquals(26, searchTF.getSize().getHeight());

    Assert.assertTrue(searchTF.isDisplayed());
    Assert.assertFalse(searchTF.isSelected());
    Assert.assertTrue(searchTF.isEnabled());

    Assert.assertEquals("input", searchTF.getTagName());
    Assert.assertEquals("", searchTF.getText());
  }
  
  @Parameterized.Parameters(name = "{0}")
  public static Iterable<Object[]> createTestParameters() {
	  List<Object[]> data = new ArrayList();
	  data.add(new Object[]{ "Firefox",  new FirefoxDriverFactory()});
	  data.add(new Object[]{ "Chrome",   new ChromeDriverFactory()});
	  data.add(new Object[]{ "IE",   new InternetExplorerDriverFactory()});
//	  data.add(new Object[]{ "HtmlUnit", new HtmlUnitDriverFactory()});
	  return data;
  }


  @Override
  public RichWebDriver getRichWebDriver() {
    return browser;
  }
}
