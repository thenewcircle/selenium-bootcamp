package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductsPage extends SpreePage {

  protected ProductsPage(RemoteWebDriver browser) {
    super(browser);
  }

  public void validateUrl() {
    String url = browser.getCurrentUrl();
    String exp = "http://spree.newcircle.com/products";
    String msg = "Found " + url;
    Assert.assertTrue(msg, url.startsWith(exp));

    // Assert.assertEquals("http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag", url);
  }

  public void validateTitle() {
    String title = browser.getTitle();
    Assert.assertEquals("Spree Demo Site", title);
  }

  public ProductPage clickProductLnk(String name) throws InterruptedException {
    WebElement productLnk = browser.findElementByLinkText(name);
    productLnk.click();

    return new ProductPage(browser);
  }
}
