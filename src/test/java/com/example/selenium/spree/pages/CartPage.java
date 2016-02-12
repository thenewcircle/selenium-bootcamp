package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CartPage extends SpreePage {

  @FindBy(partialLinkText="Continue")
  private WebElement continueLnk;
  
  public CartPage(RemoteWebDriver webDriver) {
    super(webDriver, "http://spree.newcircle.com/cart");
  }

  public void open() {
    webDriver.navigate().to("http://spree.newcircle.com/cart");
  }

  public void validateTitle() {
    Assert.assertEquals("Shopping Cart - Spree Demo Site", webDriver.getTitle());
  }

  public ProductsPage clickContinueShopping() {
    continueLnk.click();
    
    ExpectedCondition<Boolean> expectation = ExpectedConditions
        .urlToBe("http://spree.newcircle.com/products");
    new WebDriverWait(webDriver, 5).until(expectation);
    
    return new ProductsPage(webDriver);
  }
}
