package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class HomePage extends SpreePage {

  public HomePage(RemoteWebDriver webDriver) {
    // this.webDriver = webDriver;
    super(webDriver);
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com");
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
  }

  public void validateUrl() {
    String expected = "http://spree.newcircle.com/";
    String actual = webDriver.getCurrentUrl();
    Assert.assertEquals(expected, actual);
  }
}
