package com.example.selenium.labs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AdminPage extends AbstractPage {

	public AdminPage(WebDriver browser) {
		super(browser);
	}

	public void setUsername(String string) {
		browser.findElement(By.id("user_email")).sendKeys(string);
	}

	public void setPassword(String string) {
		browser.findElement(By.id("user_password")).sendKeys(string);
	}

	public void clickLoginButton() {
		browser.findElement(By.cssSelector(".cntct-us.send.signup")).click();
	}
}
