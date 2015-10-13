package com.example.selenium.labs;

import org.openqa.selenium.WebDriver;

public class HomePage extends AbstractPage {

	public static HomePage open(WebDriver browser) {
		// open shopping page in firefox
        browser.navigate().to("https://superb-store-8178.spree.mx/");
        return new HomePage(browser);
	}
	
	public HomePage(WebDriver browser) {
		super(browser);
	}
}
