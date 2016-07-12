package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends SpreePage {

  // private final RemoteWebDriver webDriver;

  public CartPage(RemoteWebDriver webDriver) {
    super(webDriver);
    //this.webDriver = webDriver;
  }

  public void validateUrl() {
    Assert.assertEquals("http://spree.newcircle.com/cart", 
                        webDriver.getCurrentUrl());
  }

  public void validateTitle() {
    Assert.assertEquals("Shopping Cart - Spree Demo Site", 
                        webDriver.getTitle());
  }
}
