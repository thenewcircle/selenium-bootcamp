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

    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/cart"));

    Assert.assertEquals("Shopping Cart - Spree Demo Site", webDriver.getTitle());
  }

  public static CartPage open(RemoteWebDriver webDriver) {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
    return new CartPage(webDriver);
  }

  public ProductsPage clickContinueShopping() {
    WebElement continueLnk = webDriver.findElementByLinkText("Continue shopping");
    continueLnk.click();

    return new ProductsPage(webDriver);
  }
}
