package com.example.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	@Deprecated
	public static void waitFor(double seconds) {
		try {
			Thread.sleep((long)seconds * 1000);
		} catch (InterruptedException e) {
			Thread.interrupted();
		}
	}

	public static void compareTitleIgnoreCase(String expectedTitle, WebDriverWait wait) {
		wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver browser) {
				return browser.getTitle().toLowerCase().startsWith(expectedTitle.toLowerCase());
			}
		});
	}

}
