package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SpreePage {

  protected final RemoteWebDriver browser;

  public SpreePage(RemoteWebDriver browser) {
    this.browser = browser;
  }

  public void validateIeComments() {
    String src = browser.getPageSource();
    String msg = "Found " + src.substring(0, 5000);
    String expected = "<!--[if lt IE 9]>\n  <script src=\"//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6/html5shiv.min.js\"></script>\n<![endif]-->";
    boolean contained = src.contains(expected);
    Assert.assertTrue(msg, contained);
  }

  public WebElement getDepartmentCmb() {
    return browser.findElementById("taxon");
  }

}
