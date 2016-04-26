package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver,
        "http://spree.newcircle.com/products",
        "Spree Demo Site");
  }

  public ProductPage clickProductLnk(String string) {
    WebElement productLnk = webDriver.findElementByLinkText("Spree Mug"); 
    productLnk.click();
    return new ProductPage(webDriver, string);
  }
}
