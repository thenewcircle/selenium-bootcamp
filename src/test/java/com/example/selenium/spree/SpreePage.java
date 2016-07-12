package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class SpreePage {

  protected final RemoteWebDriver webDriver;
  
  public SpreePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public WebElement getDepartmentCmb() {
    // return webDriver.findElementById("taxon");

    WebElement deptCmb = webDriver.findElementById("taxon");
    return deptCmb;
  }
}
