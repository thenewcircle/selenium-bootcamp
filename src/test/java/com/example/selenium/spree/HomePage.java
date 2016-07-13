package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends SpreePage {

  // private final RemoteWebDriver webDriver;

  public HomePage(RemoteWebDriver webDriver) {
    super(webDriver, ExpectedConditions.urlToBe("http://spree.newcircle.com/"));
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", 
    webDriver.getTitle());
  }

  public void validateUrl() {
    Assert.assertEquals("http://spree.newcircle.com/", 
    webDriver.getCurrentUrl());
  }

//  public WebElement getDepartmentCmb() {
//    // return webDriver.findElementById("taxon");
//
//    WebElement deptCmb = webDriver.findElementById("taxon-x");
//    return deptCmb;
//  }
}
