package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends SpreePage {

  // protected final RemoteWebDriver webDriver;

  protected ProductPage(RemoteWebDriver webDriver, String productName) {
    super(webDriver);

    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    String url = getUrl(productName);
    wait.until(ExpectedConditions.urlContains(url));
  }

  public static ProductPage open(RemoteWebDriver webDriver, String productName) {
    String url = getUrl(productName);
    webDriver.get(url);
    return new ProductPage(webDriver, productName);
  }

  protected static String getUrl(String productName) {
    String name = productName.replace(" ", "-");
    name = name.toLowerCase();
    return "http://spree.newcircle.com/products/" + name;
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
    return new CartPage(webDriver);
  }
}
