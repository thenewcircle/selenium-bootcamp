package com.example.selenium.spree;

import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class InternetExplorerDriverFactory extends WebDriverFactory {

  @Override
  public RemoteWebDriver create() {
    String path = System.getenv("webdriver.ie.driver");
    System.setProperty("webdriver.ie.driver", path);

    DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
    capabilities.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);

    return new InternetExplorerDriver(capabilities);
  }
}
