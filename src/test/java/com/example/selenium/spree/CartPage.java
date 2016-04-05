package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends SpreePage {

  public CartPage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void open() {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
  }

  public void validateTitle() {
    String actual = webDriver.getTitle();
    Assert.assertEquals("Shopping Cart - Spree Demo Site", actual);
  }

  public void validateUrl() {
    String actual = webDriver.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/cart", actual);
  }

  public ProductsPage clickContinueShopping() {
    WebElement continueLink = webDriver.findElementByLinkText("Continue shopping");
    continueLink.click();
    
    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    wait.until(ExpectedConditions.urlContains("http://spree.newcircle.com/"));

    return new ProductsPage(webDriver);
  }
}
