package com.example.selenium.labs;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleSearchTest {

    private FirefoxDriver browser;

    @Before
    public void setUp() throws Exception {
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test
    public void testGoogle() {
    	browser.navigate().to("https://www.google.com");
    	
    	Assert.assertEquals("Google", browser.getTitle());
    	Assert.assertEquals("https://www.google.com/", browser.getCurrentUrl());
    	
    	String title = "Selenium";

    	WebElement queryField = browser.findElementByName("q");
    	queryField.sendKeys(title);
    	queryField.submit();
    	
    	// SeleniumUtils.waitFor(3.0);
    	
//    	WebDriverWait wait = new WebDriverWait(browser, 10);
//        wait.until((WebDriver driver) -> driver.getTitle().toLowerCase().startsWith(title.toLowerCase()));

        waitForTitleIgnoreCase(title, 10);
    	
    	Assert.assertEquals("Selenium - Google Search", browser.getTitle());
    	Assert.assertEquals("https://www.google.com/#q=Selenium", browser.getCurrentUrl());
    }
    
    private void waitForTitleIgnoreCase(final String expectedTitle, final long seconds) {
        WebDriverWait wait = new WebDriverWait(browser, seconds);
        ExpectedCondition<Boolean> condition = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return driver.getTitle().toLowerCase().startsWith(expectedTitle.toLowerCase());
            }
        };
        wait.until(condition);
    }

    @After
    public void tearDown() {
        browser.quit();
    }
}
