package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void validateUrl() {
    String url = webDriver.getCurrentUrl();
    String expected = "http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag";
    Assert.assertEquals(expected, url);
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
  }
}