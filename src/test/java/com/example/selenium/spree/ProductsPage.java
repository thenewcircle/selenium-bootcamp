package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver, ExpectedConditions.urlContains("http://spree.newcircle.com/products"));
  }

  public void validateUrl() {
    String actual = webDriver.getCurrentUrl();
    String expected = "http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag";
    Assert.assertEquals(expected, actual);
  }

  public ProductPage clickProductLnk(String text) {
    
    WebElement link = webDriver.findElementByLinkText(text);
    link.click();
    
    return new ProductPage(webDriver);
  }
}
