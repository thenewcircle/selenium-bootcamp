package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage {

  private final RemoteWebDriver browser;

  public CartPage(RemoteWebDriver browser) {
    this.browser = browser;
  }

  public void open() {
    browser.navigate().to("http://spree.newcircle.com/cart");
  }

  public void validateTitle() {
    Assert.assertEquals("Shopping Cart - Spree Demo Site", browser.getTitle());
  }
}
