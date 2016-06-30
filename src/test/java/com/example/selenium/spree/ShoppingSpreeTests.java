package com.example.selenium.spree;

import cucumber.api.junit.Cucumber;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import ch.qos.logback.classic.Level;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.util.ArrayList;
import java.util.List;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests implements SeleniumTest {

  static {
    LogbackUtils.initLogback(Level.WARN);
  }

  private RemoteWebDriver webDriver;
  private WebDriverFactory wdf;

  @Rule
  public final ScreenshotRule screenshotRule =
          new ScreenshotRule(this, "c:\\tmp\\test-failures");

//  @After
//  public void afterMethod() {
//    webDriver.quit();
//  }

  @Override
  public RemoteWebDriver getRemoteWebDriver() {
    return webDriver;
  }

  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }

  @Before
  public void beforeMethod() throws Exception {
    webDriver = wdf.create();
  }

  @Test
  public void testGetGoogleUrl() {
    webDriver.get("http://google.com");
    String msg = "Found: " + webDriver.getCurrentUrl();
    Assert.assertTrue(msg, webDriver.getCurrentUrl().startsWith("https://www.google.com/?"));
  }

  @Test
  public void testHomePageTitle() {
    HomePage homePage = HomePage.open(webDriver);
  }

  @Test
  public void testCartPageTitle() {
    CartPage cartPage = CartPage.open(webDriver);
  }

  @Test
  public void testBackAndForth() {
    // Assume.assumeFalse(webDriver instanceof ChromeDriver);
//    String name = webDriver.getCapabilities().getBrowserName();
//    Assume.assumeFalse(name.equals("chrome"));

    webDriver.get("http://spree.newcircle.com/products/ruby-on-rails-baseball-jersey");
    Assert.assertEquals("Ruby on Rails Baseball Jersey - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().to("http://spree.newcircle.com/products/spree-baseball-jersey");
    Assert.assertEquals("Spree Baseball Jersey - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().back();
    Assert.assertEquals("Ruby on Rails Baseball Jersey - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().forward();
    Assert.assertEquals("Spree Baseball Jersey - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().refresh();
    Assert.assertEquals("Spree Baseball Jersey - Spree Demo Site", webDriver.getTitle());


    Assert.assertTrue(false);

  }

  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList();
    data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    // data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    data.add(new Object[]{ new IeDriverFactory(), "IE" });
    return data;
  }
}




