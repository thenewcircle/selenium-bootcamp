package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {

  protected final RemoteWebDriver webDriver;

  public HomePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com");
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
