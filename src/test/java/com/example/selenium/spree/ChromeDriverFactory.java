package com.example.selenium.spree;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeDriverFactory extends WebDriverFactory {

  @Override
  public RemoteWebDriver create() {
    String path = System.getenv("webdriver.chrome.driver");
    System.setProperty("webdriver.chrome.driver", path);
    return new ChromeDriver();
  }

}
