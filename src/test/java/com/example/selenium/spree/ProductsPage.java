package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver browser) {
    super(browser);
  }

  public void validateUrl() {
    String url = browser.getCurrentUrl();
    String exp = "http://spree.newcircle.com/products";
    String msg = "Found " + url;
    Assert.assertTrue(msg, url.startsWith(exp));

    // Assert.assertEquals("http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag", url);
  }

  public void validateTitle() {
    String title = browser.getTitle();
    Assert.assertEquals("Spree Demo Site", title);
  }

  public ProductPage clickProductLnk(String name) throws InterruptedException {
    WebElement productLnk = browser.findElementByLinkText(name);
    productLnk.click();

    WebDriverWait wait = new WebDriverWait(browser, 5);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/products/spree-mug"));
    
    return new ProductPage(browser);
  }
}
