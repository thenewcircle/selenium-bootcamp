package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.example.selenium.spree.utils.RichWebDriver;

public class ProductPage {

  private RichWebDriver browser;
  
  public ProductPage(RichWebDriver browser) {
    this.browser = browser;
    PageFactory.initElements(browser, this);
  }

  public void assertMainImgSrc(String string) {
    WebElement mainImg = browser.findElementByCssSelector("#main-image>img");
    String url = mainImg.getAttribute("src");
    Assert.assertTrue(url.startsWith(string));
  }

  public void clickThumbnail(int i) {
    WebElement thumbnail = browser.findElementByXPath(
        ".//*[@id='product-thumbnails']/li["+i+"]/a/img");
    thumbnail.click();
  }

  public void setQuantity(Integer i) {
    WebElement quantityTF = browser.findElementByName("quantity");
    quantityTF.clear();
    quantityTF.sendKeys(i.toString());
    
    Assert.assertEquals(i.toString(), quantityTF.getAttribute("value"));
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

  public CartPage clickAddToCart() {
    WebElement cartBtn = browser.findElementByTagName("button");

    // check background color is blue;
    // mouse over and check dark grey
    // verify it's enambed.
    
    cartBtn.click();
    return new CartPage(browser);
  }

}
