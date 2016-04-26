package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;

public class CartPage extends SpreePage {

  @FindBy(css=".continue.button.gray")
  WebElement continueLnk;
  
  public CartPage(RemoteWebDriver webDriver) {
    super(webDriver,
        "http://spree.newcircle.com/cart",
        "Shopping Cart - Spree Demo Site");
  }

  public void open() {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
  }

  public ProductsPage clickContinueShopping() {
    continueLnk.click();
    
    return new ProductsPage(webDriver);
  }
}
