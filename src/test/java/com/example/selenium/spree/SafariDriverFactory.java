package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class SafariDriverFactory implements WebDriverFactory {

  @Override
  public RemoteWebDriver create() {
    return new SafariDriver();
  }

}
