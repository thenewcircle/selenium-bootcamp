package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends SpreePage {

  // protected final RemoteWebDriver webDriver;

  public CartPage(RemoteWebDriver webDriver) {
    super(webDriver);
    // this.webDriver = webDriver;
  }

  public void open() {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
  }

  public void validateTitle() {
    org.junit.Assert.assertEquals("Shopping Cart - Spree Demo Site", webDriver.getTitle());
  }
}
