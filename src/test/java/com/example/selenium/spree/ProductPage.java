package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends SpreePage {

  public ProductPage(RemoteWebDriver webDirver) {
    super(webDirver);
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com/products/spree-tote");
  }

  public void validateTitle() {
    String actual = webDriver.getTitle();
    Assert.assertEquals("Spree Tote - Spree Demo Site", actual);
  }

  public void validateImageSrc(String expected) {
    WebElement mainImg = webDriver.findElementByCssSelector("#main-image>img");
    String actual = mainImg.getAttribute("src");
    String msg = "No match:\nActual: " + actual + "\nExpected: " + expected;
    Assert.assertTrue(msg, actual.startsWith(expected));
  }

  public void clickThumbnail(int nth) {
    nth += 1;
    String path = ".//*[@id='product-thumbnails']/li[" + nth + "]/a/img";

    WebElement thumbnail = webDriver.findElementByXPath(path);
    thumbnail.click();
  }

  public void setQuantity(int count) {
    WebElement quantityTF = webDriver.findElementByName("quantity");
    quantityTF.clear();
    quantityTF.sendKeys("3");
  }

  public CartPage clickAddToCart() {
    WebElement addBtn = webDriver.findElementByTagName("button");
    addBtn.click();

    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    wait.until(ExpectedConditions.urlContains("http://spree.newcircle.com/cart"));
    
    return new CartPage(webDriver);
  }
}
