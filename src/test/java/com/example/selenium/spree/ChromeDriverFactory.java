package com.example.selenium.spree;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class ChromeDriverFactory extends WebDriverFactory {

  @Override
  public RemoteWebDriver create() {
    System.setProperty("webdriver.chrome.driver", "C:\\tools\\selenium\\chromedriver.exe");
    return new ChromeDriver();
  }

}
