package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver, 
        "http://spree.newcircle.com/products", 
        "Spree Demo Site");
  }

//  private void validateUrl() {
//    String actual = webDriver.getCurrentUrl();
//    String expected = "http://spree.newcircle.com/products";
//    String msg = "Found: " + actual;
//    Assert.assertTrue(msg, actual.startsWith(expected));
//  }

//  private void validateTitle() {
//    String actual = webDriver.getTitle();
//    Assert.assertEquals("Spree Demo Site", actual);
//  }

  public ProductPage clickProductLnk(String text) {
    WebElement productLink = webDriver.findElementByLinkText(text);
    productLink.click();

    return new ProductPage(webDriver, text);
  }
}
