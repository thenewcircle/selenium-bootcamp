package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class CartPage extends SpreePage {

  // private final RemoteWebDriver browser;

  protected CartPage(RemoteWebDriver browser) {
    // this.browser = browser;
    super(browser);
  }

  public void open() {
    browser.navigate().to("http://spree.newcircle.com/cart");
  }

  public void validateTitle() {
    Assert.assertEquals("Shopping Cart - Spree Demo Site", browser.getTitle());
  }

  public void validateUrl() {
    String url = browser.getCurrentUrl();
    String exp = "http://spree.newcircle.com/cart";
    Assert.assertEquals(exp, url);
  }

  public ProductsPage clickContinueShopping() {
    WebElement contShopBtn = browser.findElementByLinkText("Continue shopping");
    contShopBtn.click();
    return new ProductsPage(browser);
  }
}
