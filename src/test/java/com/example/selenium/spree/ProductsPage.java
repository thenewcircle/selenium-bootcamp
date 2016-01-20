package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver browser) {
    super(browser);
  }

  public void validateUrl() {
    String url = browser.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag", url);
  }

  public void validateTitle() {
    String title = browser.getTitle();
    Assert.assertEquals("Spree Demo Site", title);
  }
}
