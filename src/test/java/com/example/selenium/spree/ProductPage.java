package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage extends SpreePage {

  public ProductPage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com/products/spree-tote");
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
  }
}
