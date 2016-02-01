package com.example.selenium.spree.factories;

import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class FirefoxDriverFactory extends WebDriverFactory {
  @Override
  public RemoteWebDriver create() {
    return new FirefoxDriver();
  }
}