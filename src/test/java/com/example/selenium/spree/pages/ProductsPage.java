package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

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

  public ProductPage controlClickProductLnk(String name) {
    webDriver.getKeyboard().pressKey(Keys.CONTROL);
    webDriver.findElementByLinkText(name).click();
    webDriver.getKeyboard().releaseKey(Keys.CONTROL);

    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    wait.until(ExpectedConditions.numberOfWindowsToBe(2));

    List<String> handles = new ArrayList<>(webDriver.getWindowHandles());
    String last = handles.get(handles.size()-1);

    webDriver.switchTo().window(last);

    return new ProductPage(webDriver, name);
  }
}