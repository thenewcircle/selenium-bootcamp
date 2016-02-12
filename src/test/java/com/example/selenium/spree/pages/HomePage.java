package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends SpreePage {

  public static HomePage open(RemoteWebDriver webDriver) {
    webDriver.get("http://spree.newcircle.com");
    webDriver.manage().deleteAllCookies();
    
    return new HomePage(webDriver);
  }

  protected HomePage(RemoteWebDriver webDriver) {
    // this.webDriver = webDriver;
    super(webDriver, "http://spree.newcircle.com/");
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", webDriver.getTitle());
  }
}
