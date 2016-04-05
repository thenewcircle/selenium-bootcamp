package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage extends SpreePage {

  public ProductPage(RemoteWebDriver webDirver) {
    super(webDirver);
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com/products/spree-tote");
  }

  public void validateTitle() {
    String actual = webDriver.getTitle();
    Assert.assertEquals("Spree Tote - Spree Demo Site", actual);
  }
  
}
