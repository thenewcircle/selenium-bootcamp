package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends SpreePage {

  // protected final RemoteWebDriver webDriver;

  public ProductPage(RemoteWebDriver webDriver) {
    super(webDriver);
    // this.webDriver = webDriver;
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com/products/spree-tote");
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Tote - Spree Demo Site", webDriver.getTitle());
  }

  public void validateImageSrc(String url) {
    WebElement img = webDriver.findElementByCssSelector("#main-image > img");
    String src = img.getAttribute("src");
    String msg = "Found " + src;
    Assert.assertTrue(msg, src.startsWith(url));
  }

  public void clickThumbnail(int index) {
    String path = String.format(".//*[@id='product-thumbnails']/li[%s]/a/img", index+1);
    webDriver.findElementByXPath(path).click();
  }

  public void setQuantity(int count) {
    WebElement quantityTF = webDriver.findElementByName("quantity");
    quantityTF.clear();
    quantityTF.sendKeys(String.valueOf(count));
  }
  
  public CartPage clickAddToCart() {
    webDriver.findElementByTagName("button").click();
    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/cart"));
    return new CartPage(webDriver);
  }
}
