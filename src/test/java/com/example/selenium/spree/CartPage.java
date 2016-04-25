package com.example.selenium.spree;

import org.junit.Assert;
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
}
