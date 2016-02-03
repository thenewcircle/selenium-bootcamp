package com.example.selenium.spree;

import ch.qos.logback.classic.Level;
import com.example.selenium.LogbackUtils;
import com.example.selenium.spree.factories.ChromeDriverFactory;
import com.example.selenium.spree.factories.WebDriverFactory;
import com.example.selenium.spree.pages.CartPage;
import com.example.selenium.spree.pages.HomePage;
import com.example.selenium.spree.pages.ProductPage;
import com.example.selenium.spree.pages.ProductsPage;
import com.gargoylesoftware.htmlunit.DefaultCssErrorHandler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Keyboard;
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
    this.webDriver.manage().deleteAllCookies();
  }

  @Test
  public void testHomePageTitle() {
    // webDriver.get("http://spree.newcircle.com");
    // Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
    HomePage homePage = HomePage.open(webDriver);
  }

  @Test
  public void testProductPageTitle() {
    ProductPage productPage = ProductPage.open(webDriver, "Spree Tote");
  }

  @Test
  public void testCartPageTitle() {
    CartPage cartPage = CartPage.open(webDriver);
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
    HomePage homePage = HomePage.open(webDriver);
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
    HomePage homePage = HomePage.open(webDriver);
    homePage.validateDepartmentCmb();
  }

  @Test
  public void testSearchSpree() throws Exception {
    HomePage homePage = HomePage.open(webDriver);

    ProductsPage productsPage = homePage.search("Bag");
    productsPage.clearSearch();

    homePage = productsPage.clickLogo();
    // Thread.sleep(3000);
  }

  @Test
  public void testShoppingSpree() throws Exception {
    HomePage homePage = HomePage.open(webDriver);

    ProductsPage productsPage = homePage.search("Mug");

    ProductPage productPage = productsPage.clickProductLnk("Spree Mug");
    productPage.validateImageSrc(
        "http://spree.newcircle.com/spree/products/45/product/spree_mug.jpeg?");
    productPage.clickThumbnail(1);
    productPage.validateImageSrc(
        "http://spree.newcircle.com/spree/products/46/product/spree_mug_back.jpeg?");
    productPage.setQuantity(3);
    productPage.validateCartLink(0, null);

    CartPage cartPage = productPage.clickAddToCart();
    cartPage.validateCartLink(3, "41.97");

    cartPage.clickContinueShopping();
  }

  @Test
  public void testRadioButtons() {
    HomePage homePage = HomePage.open(webDriver);
    ProductsPage productsPage = homePage.search("Jersey");
    ProductPage productPage = productsPage.clickProductLnk("Ruby on Rails Baseball Jersey");
    productPage.assertVariant(0);

    productPage.setQuantity(5);

    Keyboard kb = webDriver.getKeyboard();
    kb.pressKey(Keys.SHIFT);
    kb.sendKeys(Keys.TAB);
    kb.releaseKey(Keys.SHIFT);

    kb.sendKeys(Keys.DOWN);
    productPage.assertVariant(1);

    kb.sendKeys(Keys.DOWN);
    productPage.assertVariant(2);

    kb.sendKeys(Keys.DOWN);
    productPage.assertVariant(3);
  }

  @Test
  public void testMouseOver() {
    HomePage homePage = HomePage.open(webDriver);
    ProductsPage productsPage = homePage.search("Stein");

    ProductPage productPage = productsPage.clickProductLnk("Ruby on Rails Stein");
    productPage.validateImageSrc("http://spree.newcircle.com/spree/products/31/product/ror_stein.jpeg?");

    productPage.mouseOverThumbnail(1);
    productPage.validateImageSrc("http://spree.newcircle.com/spree/products/32/product/ror_stein_back.jpeg?");
  }

  @Test
  public void testNewWindow() {
    HomePage homePage = HomePage.open(webDriver);
    ProductsPage productsPage = homePage.search("Spaghetti");

    int count = webDriver.getWindowHandles().size();
    Assert.assertEquals(1, count);

    productsPage.controlClickProductLnk("Ruby on Rails Jr. Spaghetti");

    count = webDriver.getWindowHandles().size();
    Assert.assertEquals(2, count);
  }

  @Parameterized.Parameters(name = "{1}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList<>();
    // data.add(new Object[]{ new FirefoxDriverFactory(),          "Firefox" });
    // data.add(new Object[]{ new SafariDriverFactory(),           "Safari" });
    // data.add(new Object[]{ new InternetExplorerDriverFactory(), "IE" });
    data.add(new Object[]{ new ChromeDriverFactory(),           "Chrome" });
    return data;
  }
}
