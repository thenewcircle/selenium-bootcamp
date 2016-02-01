package com.example.selenium.spree.factories;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InternetExplorerDriverFactory extends WebDriverFactory {
  @Override
  public RemoteWebDriver create() {
    String path = System.getenv("webdriver.ie.driver");
    System.setProperty("webdriver.ie.driver", path);
    return new InternetExplorerDriver();
  }
}
