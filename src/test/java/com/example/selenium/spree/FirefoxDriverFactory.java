package com.example.selenium.spree;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxDriverFactory implements WebDriverFactory {

  @Override
  public RemoteWebDriver create() {
    return new FirefoxDriver();
  }

}
