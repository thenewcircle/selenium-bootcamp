package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends SpreePage {

  public HomePage(RemoteWebDriver webDriver) {
    super(webDriver);
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com");
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
  }

  public void validateUrl() {
    String actual = webDriver.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/", actual);
  }
}
