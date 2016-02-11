package com.example.selenium.spree;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InternetExplorerDriverFactory extends WebDriverFactory {

  @Override
  public RemoteWebDriver create() {
    System.setProperty("webdriver.ie.driver", "C:\\tools\\selenium\\IEDriverServer32.exe");
    return new InternetExplorerDriver();
  }
}
