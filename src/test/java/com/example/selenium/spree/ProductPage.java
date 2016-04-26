package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage extends SpreePage {

  public ProductPage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com/products/spree-tote");
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
  }

  public void validateImageSrc(String expected) {
    WebElement mainImg = webDriver.findElementByCssSelector("#main-image>img");
    String actual = mainImg.getAttribute("src");
    String msg = "Found " + actual;
    Assert.assertTrue(msg, actual.contains(expected));
  }

  public void clickThumbnail(int nth) {
    nth = nth + 1;
    String path = ".//*[@id='product-thumbnails']/li[" + nth + "]/a/img";
    WebElement thumbNail = webDriver.findElementByXPath(path);

    thumbNail.click();
  }

  public void setQuantity(int quantity) {
    WebElement quantityTF = webDriver.findElementByName("quantity");
    quantityTF.clear();
    quantityTF.sendKeys(String.valueOf(quantity));
    // quantityTF.sendKeys(str(quantity));
  }

  public CartPage clickAddToCart() {
    WebElement cartBtn = webDriver.findElementByTagName("button");
    cartBtn.click();

    return new CartPage(webDriver);
  }
}
