package com.example.selenium;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SeleniumUtils {

	// default (package) - ignored
	// private 
	// protected
	// public
	
	
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

	public static void contains(String expectedSubstring, String actualString) {

        if (actualString == null) {
            String msg = String.format("expected to contain \"%s\" but got null.", expectedSubstring);
            Assert.fail(msg);
        }
        if (expectedSubstring == null) {
            throw new IllegalArgumentException("expectedString shouldn't be null");
        }
        if (!actualString.contains(expectedSubstring)) {
            String msg = String.format("expected to contain \"%s\" but got \"%s\".", expectedSubstring, actualString);
            Assert.fail(msg);
        }

    }

	public static By imageWithAltText(String alternateText) {
		return By.cssSelector("img[alt='"+alternateText+"']");
	}
}
