package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {

  private RemoteWebDriver webDriver;

  public HomePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void open() {
    webDriver.get("http://spree.newcircle.com");
  }
}
