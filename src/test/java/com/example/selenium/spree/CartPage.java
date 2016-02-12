package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class CartPage extends SpreePage {

  // protected final RemoteWebDriver webDriver;

  public CartPage(RemoteWebDriver webDriver) {
    // this.webDriver = webDriver;
    super(webDriver);
  }

  public void open() {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
  }

  public void validateTitle() {
    Assert.assertEquals("Shopping Cart - Spree Demo Site", webDriver.getTitle());
  }
}
