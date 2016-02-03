package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends SpreePage {

  @FindBy(css = "#main-image > img")
  protected WebElement img;

  @FindBy(name = "quantity")
  protected WebElement quantityTF;

  @FindBy(tagName = "button")
  protected WebElement addToCartBtn;

  protected ProductPage(RemoteWebDriver webDriver, String productName) {
    super(webDriver);

    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    String url = getUrl(productName);
    wait.until(ExpectedConditions.urlContains(url));

    String title = productName + " - Spree Demo Site";
    Assert.assertEquals(title, webDriver.getTitle());
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

  public void validateImageSrc(String url) {
    String src = img.getAttribute("src");
    String msg = "Found " + src;
    Assert.assertTrue(msg, src.startsWith(url));
  }

  public void clickThumbnail(int index) {
    // Cannot replace with @FindBy because it's dynamically created
    String path = String.format(".//*[@id='product-thumbnails']/li[%s]/a/img", index+1);
    webDriver.findElementByXPath(path).click();
  }

  public void setQuantity(int count) {
    quantityTF.clear();
    quantityTF.sendKeys(String.valueOf(count));
  }

  public CartPage clickAddToCart() {
    addToCartBtn.click();
    return new CartPage(webDriver);
  }
}
