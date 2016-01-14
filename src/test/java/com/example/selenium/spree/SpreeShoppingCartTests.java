package com.example.selenium.spree;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;

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
public class SpreeShoppingCartTests implements SeleniumTest {

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
  
  public SpreeShoppingCartTests(String name, WebDriverFactory wdf) {
    this.wdf = wdf;
  }

  @Before
  public void beforeMethod() throws Exception {
    browser = wdf.create();
    browser.manage().timeouts()
      .implicitlyWait(10, TimeUnit.SECONDS);

    browser.get("http://spree.newcircle.com");
    browser.manage().deleteAllCookies();
  }


  @Override
  public RichWebDriver getRichWebDriver() {
    return browser;
  }

  @Test
  public void testStein() throws Exception {
    HomePage homePage = HomePage.open(browser);
    ProductsPage productsPage = homePage.search("Stein");
    ProductPage productPage = productsPage.clickProductLink("Ruby on Rails Stein");
    productPage.setQuantity(13);
    productPage.assertCartLink(0, null);
    CartPage cartPage = productPage.clickAddToCart();
    cartPage.assertCartLink(13, "220.87");
  }
  
  @Test
  public void testShoppingSpree2() throws Exception {
    HomePage homePage = HomePage.open(browser);
    homePage.assertUrl();

    ProductsPage productsPage = homePage.search("Mug");

    ProductPage productPage = productsPage.clickProductLink("Spree Mug");

    productPage.assertMainImgSrc(
      "http://spree.newcircle.com/spree/products/45/product/spree_mug.jpeg?");

    productPage.clickThumbnail(2); // 1-based index
    productPage.assertMainImgSrc(
       "http://spree.newcircle.com/spree/products/46/product/spree_mug_back.jpeg");

    productPage.setQuantity(3);
    productPage.assertCartLink(0, null);
    CartPage cartPage = productPage.clickAddToCart();
     
    cartPage.assertCartLink(3, "41.97");
    productsPage = cartPage.continueShopping();
    productsPage.assertUrl();  
  }
  
  @Test
  public void testShoppingSpree1() throws Exception {
    browser.get("http://spree.newcircle.com");
    
    WebElement keywordsTF = browser.findElementById("keywords");
    keywordsTF.sendKeys("Mug");
    
    WebElement searchBtn = browser
        .findElementByCssSelector(
            "#search-bar>form>input[type='submit']");
    searchBtn.click();
    
    WebElement productLnk = browser.findElementByLinkText("Spree Mug");
    productLnk.click();
    
    WebElement mainImg = browser.findElementByCssSelector("#main-image>img");
    String url = mainImg.getAttribute("src");
    Assert.assertTrue(url.startsWith(
        "http://spree.newcircle.com/spree/products/45/product/spree_mug.jpeg?"));

    WebElement thumbnail = browser.findElementByXPath(
        ".//*[@id='product-thumbnails']/li[2]/a/img");
    thumbnail.click();
    
    url = mainImg.getAttribute("src");
    Assert.assertTrue(url.startsWith(
        "http://spree.newcircle.com/spree/products/46/product/spree_mug_back.jpeg?"));
  
    WebElement quantityTF = browser.findElementByName("quantity");
    quantityTF.clear();
    quantityTF.sendKeys("3");
    
    Assert.assertEquals("3", quantityTF.getAttribute("value"));
    
    WebElement cartLnk = browser.findElementByClassName("cart-info");
    Assert.assertEquals("CART: (EMPTY)", cartLnk.getText());

    WebElement addToCartBtn = browser.findElementByTagName("button");
    addToCartBtn.click();
    
    url = browser.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/cart", url);
    
    WebElement continueBtn = browser.findElementByLinkText("Continue shopping");
    continueBtn.click();
    
    url = browser.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/products", url);
  }
  
  @Parameterized.Parameters(name = "{0}")
  public static Iterable<Object[]> createTestParameters() {
    List<Object[]> data = new ArrayList<>();
    data.add(new Object[]{ "Firefox",  new FirefoxDriverFactory()});
    data.add(new Object[]{ "Chrome",   new ChromeDriverFactory()});
    data.add(new Object[]{ "IE",   new InternetExplorerDriverFactory()});
    // data.add(new Object[]{ "HtmlUnit", new HtmlUnitDriverFactory()});
    return data;
  }
}
