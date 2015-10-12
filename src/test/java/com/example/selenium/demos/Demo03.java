package com.example.selenium.demos;

import static org.junit.Assert.assertEquals;

import java.util.concurrent.TimeUnit;
import static com.example.selenium.SeleniumUtils.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Demo03 {
    FirefoxDriver browser;
    
    @Before
    public void setUp() throws Exception {
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().window().maximize();
    }

    @Test
    public void demoSearchGoogle() {
    	// not secure, not www, browser will follow redirects
    	browser.navigate().to("http://google.com");
    	WebElement searchBox = browser.findElement(By.name("q"));
    	
    	searchBox.sendKeys("Kittens");
    	searchBox.submit();

    	String expectedTitle = "kittens"; 

    	// SeleniumUtils.waitFor(6.0);
    	WebDriverWait wait = new WebDriverWait(browser, 10);
    	compareTitleIgnoreCase(expectedTitle, wait);

    	wait = new WebDriverWait(browser, 10);
        wait.until((WebDriver driver) -> driver.getTitle().toLowerCase().startsWith(expectedTitle.toLowerCase()));

    	String actualTitle = browser.getTitle();
    	assertEquals("Kittens - Google Search", actualTitle);
    }

	@After
    public void tearDown() {
        browser.quit();
    }
}