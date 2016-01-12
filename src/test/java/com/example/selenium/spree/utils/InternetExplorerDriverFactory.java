package com.example.selenium.spree.utils;

import org.openqa.selenium.ie.InternetExplorerDriver;

public class InternetExplorerDriverFactory extends WebDriverFactory {

	@Override
	public RichWebDriver create() {
		  System.setProperty("webdriver.ie.driver", 
		             "C:\\tools\\selenium\\IEDriverServer32.exe");

	  InternetExplorerDriver webDriver = new InternetExplorerDriver();
	  return new RichWebDriver(webDriver);
	}

}
