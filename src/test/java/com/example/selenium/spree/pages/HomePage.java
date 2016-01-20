package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
// import junit.framework.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends SpreePage {

  protected HomePage(RemoteWebDriver browser) {
    super(browser);

    WebDriverWait wait = new WebDriverWait(browser, 5, 1000);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/"));

    Assert.assertEquals("Spree Demo Site", browser.getTitle());
  }

  public static HomePage open(RemoteWebDriver browser) {
    browser.get("http://spree.newcircle.com");    
    return new HomePage(browser);
  }
}
