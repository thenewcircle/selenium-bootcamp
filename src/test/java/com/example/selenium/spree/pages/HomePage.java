package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends SpreePage {

  // protected final RemoteWebDriver webDriver;

  protected HomePage(RemoteWebDriver webDriver) {
    super(webDriver);

    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/"));
  }

  public static HomePage open(RemoteWebDriver webDriver) {
    webDriver.get("http://spree.newcircle.com");
    return new HomePage(webDriver);
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
  }

  public void validateIeComments() {
    String src = webDriver.getPageSource();
    String msg = "Found " + src.substring(0, 1000);
    Assert.assertTrue(msg, src.contains("<!--[if lt IE 9]>"));
  }
}
