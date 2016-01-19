package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage {

  private final RemoteWebDriver browser;

  public ProductPage(RemoteWebDriver browser) {
    this.browser = browser;
  }

  public void open() {
    browser.get("http://spree.newcircle.com/products/spree-tote");
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Tote - Spree Demo Site", browser.getTitle());
  }
  
}
