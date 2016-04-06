package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ProductPage extends SpreePage {

  public ProductPage(RemoteWebDriver webDirver, String product) {
    super(webDirver, 
        toUrl(product),
        product+" - Spree Demo Site");
  }

  public static ProductPage open(RemoteWebDriver webDriver, String product) {
    webDriver.get(toUrl(product));
    return new ProductPage(webDriver, product);
  }

  public static String toUrl(String product) {
    String name = product.toLowerCase();
    name = name.replace(" ", "-");
    name = name.replace(".", "");
    return "http://spree.newcircle.com/products/" + name;
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
    
    return new CartPage(webDriver);
  }
}
