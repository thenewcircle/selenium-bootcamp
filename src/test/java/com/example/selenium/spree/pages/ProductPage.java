package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductPage extends SpreePage {

  // private final RemoteWebDriver browser;

  protected ProductPage(RemoteWebDriver browser) {
    super(browser);

    WebDriverWait wait = new WebDriverWait(browser, 5);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/products/spree-mug"));
}

  public void open() {
    browser.get("http://spree.newcircle.com/products/spree-tote");
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Tote - Spree Demo Site", browser.getTitle());
  }

  public void validateImageSrc(String url) {
    WebElement mainImg = browser.findElementByCssSelector("#main-image>img");
    String src = mainImg.getAttribute("src");
    String msg = "Found " + src;
    Assert.assertTrue(msg, src.startsWith(url));
  }

  public void clickThumbnail(int index) {
    String path = String.format(".//*[@id='product-thumbnails']/li[%s]/a/img", index+1);
    WebElement thumbnail = browser.findElementByXPath(path);
    thumbnail.click();
  }

  public void setQuantity(int count) {
    WebElement quantityTF = browser.findElementByName("quantity");
    quantityTF.clear();
    quantityTF.sendKeys(String.valueOf(count));
  }

  public CartPage clickAddToCart() {
    WebElement addToCartBtn = browser.findElementByTagName("button");
    addToCartBtn.click();
    return new CartPage(browser);
  }
}
