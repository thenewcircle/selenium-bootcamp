package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage extends SpreePage {

  public HomePage(RemoteWebDriver webDriver) {
    super(webDriver, 
          "http://spree.newcircle.com/", 
          "Spree Demo Site");
  }

  public static HomePage open(RemoteWebDriver webDriver) {
    webDriver.get("http://spree.newcircle.com");
    return new HomePage(webDriver);
  }
}
