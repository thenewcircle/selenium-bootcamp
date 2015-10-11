package com.example.selenium.demo03;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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
    	searchBox.sendKeys("kittens");
    	searchBox.submit();

    	String expectedTitle = "kittens - Google Search"; 
    	
    	WebDriverWait wait = new WebDriverWait(browser, 10);
        wait.until(new ExpectedCondition<Boolean>() {
			@Override
			public Boolean apply(WebDriver driver) {
				return driver.getTitle().toLowerCase().startsWith(expectedTitle.toLowerCase());
			}
        });
    }

    @Deprecated
    public void waitFor(double seconds) {
        long wait = (long) (1000.0 * seconds);
        try {
            Thread.sleep(wait);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }    
    
    @After
    public void tearDown() {
        browser.quit();
    }
}
