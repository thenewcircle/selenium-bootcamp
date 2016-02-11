package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage {

  protected final RemoteWebDriver webDriver;

  public CartPage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void open() {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
  }

  public void validateTitle() {
    Assert.assertEquals("Shopping Cart - Spree Demo Site", webDriver.getTitle());
  }
}
