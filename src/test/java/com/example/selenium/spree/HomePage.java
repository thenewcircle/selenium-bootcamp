package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
// import junit.framework.*;

public class HomePage extends SpreePage {

  public HomePage(RemoteWebDriver browser) {
    super(browser);
  }

  public void open() {
    browser.get("http://spree.newcircle.com");    
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", browser.getTitle());
  }
}
