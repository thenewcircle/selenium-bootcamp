package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;

public class HomePage {

  private final RemoteWebDriver webDriver;

  public HomePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", 
    webDriver.getTitle());
  }

  public void validateUrl() {
    Assert.assertEquals("http://spree.newcircle.com/", 
    webDriver.getCurrentUrl());
  }
}
