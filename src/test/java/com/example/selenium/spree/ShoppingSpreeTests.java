package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.LogbackUtils;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RunWith(Parameterized.class)
public class ShoppingSpreeTests implements SeleniumTest {

  static {
    LogbackUtils.initLogback(Level.WARN);
    LogbackUtils.setLevel(DefaultCssErrorHandler.class, Level.ERROR);
  }

  private RemoteWebDriver webDriver;
  private final WebDriverFactory wdf;

  @Rule
  public final ScreenshotRule screenshotRule =
      new ScreenshotRule(this, "c:\\tmp\\test-failures");

  public ShoppingSpreeTests(WebDriverFactory wdf, String name) {
    this.wdf = wdf;
  }

  @Override
  public RemoteWebDriver getRemoteWebDriver() {
    return webDriver;
  }

  @Before
  public void beforeMethod() throws Exception {
    // HtmlUnitDriver wd= new HtmlUnitDriver();
    // wd.setJavascriptEnabled(true);
    // FirefoxDriver wd = new FirefoxDriver();

    // String path = System.getenv("webdriver.chrome.driver");
    // System.setProperty("webdriver.chrome.driver", path);
    // ChromeDriver wd = new ChromeDriver();

    // String path = System.getenv("webdriver.ie.driver");
    // System.setProperty("webdriver.ie.driver", path);
    // InternetExplorerDriver wd = new InternetExplorerDriver();

    this.webDriver = wdf.create();
    this.webDriver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
  }

  @Test
  public void testHomePageTitle() {
    // webDriver.get("http://spree.newcircle.com");
    // Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    homePage.validateTitle();
  }

  @Test
  public void testProductPageTitle() {
    ProductPage productPage = new ProductPage(webDriver);
    productPage.open();
    productPage.validateTitle();
  }

  @Test
  public void testCartPageTitle() {
    CartPage cartPage = new CartPage(webDriver);
    cartPage.open();
    cartPage.validateTitle();
  }

  @Test
  public void testBackAndForth() {
    webDriver.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().to("http://spree.newcircle.com/products/spree-tote");
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().back();
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().forward();
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
  }

  @Test
  public void testRefresh() {
    webDriver.get("http://spree.newcircle.com/products/spree-bag");
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());

    webDriver.navigate().refresh();
    Assert.assertEquals("Spree Bag - Spree Demo Site", webDriver.getTitle());
  }

  @Test
  public void testGetGoogleUrl() {
    webDriver.get("http://google.com");
    Assert.assertEquals("Google", webDriver.getTitle());
    String url = webDriver.getCurrentUrl();

    String msg = "Found " + url;
    Assert.assertTrue(msg, url.startsWith("https://www.google.com/?"));
  }

  @Test
  public void testIeComments() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    homePage.validateIeComments();
  }

  @Test
  public void testCapabilities() {

    Assert.assertEquals(true, webDriver.getCapabilities().isJavascriptEnabled());

    if (webDriver instanceof FirefoxDriver) {
      Assert.assertEquals("firefox", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("43.0.4", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", webDriver.getCapabilities().getPlatform().name());

    } else if (webDriver instanceof ChromeDriver) {
      Assert.assertEquals("chrome", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("48.0.2564.97", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("XP", webDriver.getCapabilities().getPlatform().name());

    } else if (webDriver instanceof InternetExplorerDriver) {
      Assert.assertEquals("internet explorer", webDriver.getCapabilities().getBrowserName());
      Assert.assertEquals("11", webDriver.getCapabilities().getVersion());
      Assert.assertEquals("WINDOWS", webDriver.getCapabilities().getPlatform().name());

    } else {
      throw new UnsupportedOperationException();
    }
  }

  @Test
  public void testDepartmentsCombo() {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();
    homePage.validateDepartmentCmb();
  }

  @Test
  public void testSearchSpree() throws Exception {
    HomePage homePage = new HomePage(webDriver);
    homePage.open();

    ProductsPage productsPage = homePage.search("Bag");
    productsPage.validateUrl();
    productsPage.validateTitle();
    productsPage.clearSearch();

    homePage = productsPage.clickLogo();
    // Thread.sleep(3000);
    // homePage.validateUrl();
  }

  // @After
  // public void afterMethod() {
  //   webDriver.quit();
  // }

  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList();
    // data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    // data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    return data;
  }
}
