package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import com.example.selenium.spree.utils.RichWebDriver;

public class ProductsPage {

  private RichWebDriver browser;
  
  public ProductsPage(RichWebDriver browser) {
    this.browser = browser;
    PageFactory.initElements(browser, this);
  }

  public ProductPage clickProductLink(String string) {
    WebElement productLnk = browser.findElementByLinkText(string);
    productLnk.click();
    return new ProductPage(browser);
  }

  public void assertUrl() {
    String url = browser.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/products", url);
  }
}
