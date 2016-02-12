package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends SpreePage {

  @CacheLookup
  @FindBy(css="#main-image>img")
  private WebElement mainImg;;

  @FindBy(name="quantity")
  private WebElement quantityTF;

  @FindBy(tagName="button")
  private WebElement addToCartBtn;
  
  private static String buildUrl(String productName) {
    return "http://spree.newcircle.com/products/" + 
        productName.replace(" ", "-").replace(".", "").toLowerCase();
  }
  
  public static ProductPage open(RemoteWebDriver webDriver, String productName) {
    webDriver.get(buildUrl(productName));
  
    return new ProductPage(webDriver, productName);
  }

  
  public ProductPage(RemoteWebDriver webDriver, String productName) {
    super(webDriver, buildUrl(productName));
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
  }

  public void validateImageSrc(String expected) {
    String actual = mainImg.getAttribute("src");
    String msg = "Found " + actual;
    Assert.assertTrue(msg, actual.startsWith(expected));
  }

  public void mouseOverThumbnail(int i) throws Exception {
    //webDriver.getMouse().mouseDown(where);
    
    i += 1;
    String path = ".//*[@id='product-thumbnails']/li["+i+"]";
    RemoteWebElement thumbnail = (RemoteWebElement)webDriver.findElementByXPath(path);
    // Coordinates co = ((Locatable)thumbnail).getCoordinates();
    webDriver.getMouse().mouseMove(thumbnail.getCoordinates());

    System.out.println("After we move the mouse");
    Thread.sleep(10*1000);
}

  public void clickThumbnail(int i) {
    i += 1;
    String path = ".//*[@id='product-thumbnails']/li["+i+"]/a";
    WebElement thumbnail = webDriver.findElementByXPath(path);
    thumbnail.click();
  }

  public void setQuantity(int i) {
    quantityTF.clear();
    quantityTF.sendKeys("3");
  }

  public CartPage clickAddToCart() {
    addToCartBtn.click();

    ExpectedCondition<Boolean> expectation = ExpectedConditions
        .urlToBe("http://spree.newcircle.com/cart");
    new WebDriverWait(webDriver, 5).until(expectation);

    return new CartPage(webDriver);
  }
}










