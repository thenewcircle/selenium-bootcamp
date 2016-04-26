package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage extends SpreePage {

  public ProductPage(RemoteWebDriver webDriver, String name) {
    super(webDriver,
        toUrl(name),
        name + " - Spree Demo Site");
  }

  private static String toUrl(String name) {
    name = name.toLowerCase();
    name = name.replace(" ", "-").replace(".", "");
    return "http://spree.newcircle.com/products/" + name;
  }

  public static ProductPage open(RemoteWebDriver webDriver, String name) {
    webDriver.get(toUrl(name));
    return new ProductPage(webDriver, name);
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
