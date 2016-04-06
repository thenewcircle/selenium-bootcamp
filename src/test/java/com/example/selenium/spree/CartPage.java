package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends SpreePage {

  public CartPage(RemoteWebDriver webDriver) {
    super(webDriver,
        "http://spree.newcircle.com/cart",
        "Shopping Cart - Spree Demo Site");

    // Check the button color.
    // Default quantity 1
    // Check image url
    // Check main product name
  }

  public static CartPage open(RemoteWebDriver webDriver) {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
    return new CartPage(webDriver);
  }

//  public void validateTitle() {
//    String actual = webDriver.getTitle();
//    Assert.assertEquals("Shopping Cart - Spree Demo Site", actual);
//  }

//  public void validateUrl() {
//    String actual = webDriver.getCurrentUrl();
//    Assert.assertEquals("http://spree.newcircle.com/cart", actual);
//  }

  public ProductsPage clickContinueShopping() {
    WebElement continueLink = webDriver.findElementByLinkText("Continue shopping");
    continueLink.click();
    
    return new ProductsPage(webDriver);
  }
}
