package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.internal.Locatable;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ProductPage extends SpreePage {

  @FindBy(css = "#main-image > img")
  protected WebElement img;

  @FindBy(name = "quantity")
  protected WebElement quantityTF;

  @FindBy(tagName = "button")
  protected WebElement addToCartBtn;

  @CacheLookup
  @FindBy(css="#product-variants input")
  protected List<WebElement> variants;

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

  public void assertVariant(int index) {
    for (int i = 0; i < variants.size(); i++) {
      WebElement variant = variants.get(i);
      String msg = "Variant " + i;
      if (i == index) {
        Assert.assertTrue(msg, variant.isSelected());
      } else {
        Assert.assertFalse(msg, variant.isSelected());
      }
    }
  }

  public void mouseOverThumbnail(int index) {
    WebElement thumbnail = webDriver.findElementByXPath(".//*[@id='product-thumbnails']/li["+ (index+1) +"]/a/img");
    Coordinates co = ((Locatable)thumbnail).getCoordinates();
    webDriver.getMouse().mouseMove(co);
  }
}
