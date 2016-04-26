package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends SpreePage {

  public CartPage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void open() {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
  }

  public void validateTitle() {
    String actual = webDriver.getTitle();
    Assert.assertEquals("Shopping Cart - Spree Demo Site", actual);
  }

  public void validateUrl() {
    String expected = "http://spree.newcircle.com/cart";
    String actual = webDriver.getCurrentUrl();
    Assert.assertEquals(expected, actual);
  }

  public ProductsPage clickContinueShopping() {
    WebElement continueLnk = webDriver.findElementByCssSelector(".continue.button.gray");
    continueLnk.click();
    
    return new ProductsPage(webDriver);
  }
}
