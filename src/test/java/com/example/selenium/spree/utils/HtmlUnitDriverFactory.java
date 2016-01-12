package com.example.selenium.spree.utils;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class HtmlUnitDriverFactory extends WebDriverFactory {

	@Override
	public RichWebDriver create() {
		HtmlUnitDriver webDriver = new HtmlUnitDriver();
		webDriver.setJavascriptEnabled(true);
		return new RichWebDriver(webDriver);
	}

}
