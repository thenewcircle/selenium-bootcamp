package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends SpreePage {

  // protected final RemoteWebDriver webDriver;

  public HomePage(RemoteWebDriver webDriver) {
    super(webDriver);
    // this.webDriver = webDriver;
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

  public void validateUrl() {
    String url = webDriver.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/", url);
  }
}
