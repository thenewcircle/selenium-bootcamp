package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class CartPage extends SpreePage {

  // private final RemoteWebDriver webDriver;

  public CartPage(RemoteWebDriver webDriver) {
    super(webDriver, ExpectedConditions.urlToBe("http://spree.newcircle.com/cart"));
  }

  public void validateUrl() {
    Assert.assertEquals("http://spree.newcircle.com/cart", 
                        webDriver.getCurrentUrl());
  }

  public void validateTitle() {
    Assert.assertEquals("Shopping Cart - Spree Demo Site", 
                        webDriver.getTitle());
  }

  public ProductsPage clickContinueShopping() {
    WebElement contShop = webDriver.findElementByLinkText("Continue shopping");
    contShop.click();
    
    return new ProductsPage(webDriver);
  }
}
