package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SpreePage {

  RemoteWebDriver webDriver;

  public SpreePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public WebElement getDepartmentCmb() {
    return webDriver.findElementById("taxon");
  }

  public boolean isThisFirefox() {
    String name = webDriver.getCapabilities().getBrowserName();
    return "firefox".equals(name);
  }

  public boolean isThisIe() {
    String name = webDriver.getCapabilities().getBrowserName();
    return "internet explorer".equals(name);
  }

  public boolean isThisIeVersion6() {
    String name = webDriver.getCapabilities().getBrowserName();
    String version = webDriver.getCapabilities().getVersion();
    return "internet explorer".equals(name) && 
           "6".equals(version);
  }
}
