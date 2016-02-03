package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends SpreePage {

  protected ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver);

    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    wait.until(ExpectedConditions.urlContains("http://spree.newcircle.com/products"));

    Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
  }

  public ProductPage clickProductLnk(String name) {
    // Cannot replace with @FindBy because it's dynamically created
    webDriver.findElementByLinkText(name).click();
    return new ProductPage(webDriver, name);
  }
}