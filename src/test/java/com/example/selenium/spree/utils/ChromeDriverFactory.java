package com.example.selenium.spree.utils;

import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeDriverFactory extends WebDriverFactory  {

	@Override
	public RichWebDriver create() {
	  System.setProperty("webdriver.chrome.driver", 
		             "C:\\tools\\selenium\\chromedriver.exe");

	  ChromeDriver webDriver = new ChromeDriver();
	  return new RichWebDriver(webDriver);
	}

}
