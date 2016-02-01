package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends SpreePage {

  // protected final RemoteWebDriver webDriver;

  protected CartPage(RemoteWebDriver webDriver) {
    super(webDriver);
    // this.webDriver = webDriver;
  }

  public static CartPage open(RemoteWebDriver webDriver) {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
    return new CartPage(webDriver);
  }

  public void validateTitle() {
    Assert.assertEquals("Shopping Cart - Spree Demo Site", webDriver.getTitle());
  }

  public void validateUrl() {
    String url = "http://spree.newcircle.com/cart";
    Assert.assertEquals(url, webDriver.getCurrentUrl());
  }

  public ProductsPage clickContinueShopping() {
    WebElement continueLnk = webDriver.findElementByLinkText("Continue shopping");
    continueLnk.click();

    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/products"));

    return new ProductsPage(webDriver);
  }
}
