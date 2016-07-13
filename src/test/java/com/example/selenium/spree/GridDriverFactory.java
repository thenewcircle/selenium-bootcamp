package com.example.selenium.spree;

import java.net.URI;

import org.openqa.selenium.Platform;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GridDriverFactory implements WebDriverFactory {

  @Override
  public RemoteWebDriver create() throws Exception {
    URI uri = URI.create("http://10.102.33.87:4444/wd/hub");
    DesiredCapabilities capability = DesiredCapabilities.firefox();
    // capability.setBrowserName("firefox");
    // capability.setPlatform(Platform.LINUX);
    
    RemoteWebDriver driver = new RemoteWebDriver(uri.toURL(), capability);
      
    return driver;
  }
}
