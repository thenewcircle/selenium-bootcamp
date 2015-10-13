package com.example.selenium.labs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import com.example.selenium.SeleniumUtils;

public class HomePage extends AbstractPage {

	public static HomePage open(WebDriver browser) {
		// open shopping page in firefox
        browser.navigate().to("https://superb-store-8178.spree.mx/");
        return new HomePage(browser);
        // return PageFactory.initElements(browser, HomePage.class);
	}
	
	public HomePage(WebDriver browser) {
		super(browser);
	}

	public AdminPage clickAdminLinkInTopNavigationBar() {
		browser.findElement(By.linkText("ADMIN")).click();
		return new AdminPage(browser);
	}
}
