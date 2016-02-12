package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class SpreePage {

  protected final RemoteWebDriver webDriver;

  public SpreePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void validateIeComments() {
    String content = webDriver.getPageSource();
    String msg = "Found: " + content.substring(0,  1000);
    Assert.assertTrue(msg, content.contains("<!--[if lt IE 9]>"));
  }

  public WebElement getDepartmentCmb() {
    return webDriver.findElementById("taxon");
  }

}
