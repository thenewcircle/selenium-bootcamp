package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void validateUrl() {
    String actual = webDriver.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag", actual);
  }

  public void validateTitle() {
    String actual = webDriver.getTitle();
    Assert.assertEquals("Spree Demo Site", actual);
  }
}
