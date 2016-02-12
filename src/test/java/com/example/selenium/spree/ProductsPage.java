package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void validateUrl() {
    String actual = webDriver.getCurrentUrl();
    String expected = "http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag";
    Assert.assertEquals(expected, actual);
  }

  public void validateTitle() {
    String actual = webDriver.getTitle();
    String expected = "Spree Demo Site";
    Assert.assertEquals(expected, actual);
  }
}
