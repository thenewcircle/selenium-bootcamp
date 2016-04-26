package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void validateUrl() {
    String actual = webDriver.getCurrentUrl();
    String expected = "http://spree.newcircle.com/products";
    String msg = "Found " + actual;
    Assert.assertTrue(msg, actual.contains(expected));
  }

  public void validateTitle() {
    String actual = webDriver.getTitle();
    String expected = "Spree Demo Site";
    Assert.assertEquals(expected, actual);
  }

  public ProductPage clickProductLnk(String string) {
    WebElement productLnk = webDriver.findElementByLinkText("Spree Mug"); 
    productLnk.click();
    return new ProductPage(webDriver);
  }
}
