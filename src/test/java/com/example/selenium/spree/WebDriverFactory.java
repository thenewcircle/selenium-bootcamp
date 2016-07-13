package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;

public interface WebDriverFactory {

  RemoteWebDriver create() throws Exception;

}
