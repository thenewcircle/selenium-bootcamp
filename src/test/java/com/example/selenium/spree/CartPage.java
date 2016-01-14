package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.example.selenium.spree.utils.RichWebDriver;

public class CartPage {

  private RichWebDriver browser;
  
  public CartPage(RichWebDriver browser) {
    this.browser = browser;
    PageFactory.initElements(browser, this);
  }

  public void assertCartLink(int quantity, String dollars) {
    WebElement cartLnk = browser.findElementByClassName("cart-info");
    if (quantity == 0) {
      Assert.assertEquals("CART: (EMPTY)", cartLnk.getText());
    } else {
      String expected = String.format("CART: (%s) $%s", quantity, dollars);
      Assert.assertEquals(expected, cartLnk.getText());
    }
  }

  public ProductsPage continueShopping() {
    WebElement continueBtn = browser.findElementByLinkText("Continue shopping");
    continueBtn.click();
    return new ProductsPage(browser);
  }
}
