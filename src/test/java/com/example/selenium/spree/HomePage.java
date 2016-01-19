package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.remote.RemoteWebDriver;
// import junit.framework.*;

public class HomePage {

  private final RemoteWebDriver browser;

  public HomePage(RemoteWebDriver browser) {
    this.browser = browser;
  }

  public void open() {
    browser.get("http://spree.newcircle.com");    
  }

  public void validateTitle() {
    Assert.assertEquals("Spree Demo Site", browser.getTitle());
  }

  public void validateIeComments() {
    String src = browser.getPageSource();
    String msg = "Found " + src.substring(0, 5000);
    String expected = "<!--[if lt IE 9]>\n  <script src=\"//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6/html5shiv.min.js\"></script>\n<![endif]-->";
    boolean contained = src.contains(expected);
    Assert.assertTrue(msg, contained);
  }
  
}
