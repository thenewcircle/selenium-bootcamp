package com.example.selenium.spree.utils;

import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxDriverFactory extends WebDriverFactory {

	@Override
	public RichWebDriver create() {
		FirefoxDriver webDriver = new FirefoxDriver();
		return new RichWebDriver(webDriver);
	}

}
