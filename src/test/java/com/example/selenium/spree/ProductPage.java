package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class ProductPage extends SpreePage {

  public ProductPage(RemoteWebDriver webDriver) {
    super(webDriver, ExpectedConditions
        .urlToBe("http://spree.newcircle.com/products/spree-mug"));
  }

  public void validateImageSrc(String expected) {
    WebElement mainImg = webDriver.findElementByCssSelector("#main-image>img");
    String actual = mainImg.getAttribute("src");
    Assert.assertEquals(expected, actual);
  }

  public void clickThumbnail(int i) {
    i = i + 1; // convert to zero base.
    String path = ".//*[@id='product-thumbnails']/li["+i+"]/a/img";
    WebElement thmb = webDriver.findElementByXPath(path);
    thmb.click();
  }

  public void setQuantity(Integer amount) {
    WebElement quantityTF = webDriver.findElementByName("quantity");
    quantityTF.clear();
    quantityTF.sendKeys(amount.toString());
  }

  public CartPage clickAddToCart() {
    WebElement addBtn = webDriver.findElementByTagName("button");
    addBtn.click();
    
    return new CartPage(webDriver);
  }
}







