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
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Demo03 {
    FirefoxDriver browser;
    
    @Before
    public void setUp() throws Exception {
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().window().maximize();
    }

    @Test
    public void demoSearchGoogle() throws Exception {
    	// not secure, not www, browser will follow redirects
    	browser.navigate().to("http://google.com");
    	WebElement searchBox = browser.findElement(By.name("q"));
    	searchBox.sendKeys("kittens");
    	searchBox.submit();
    	
    	String title = browser.getTitle();
    	Assert.assertEquals("kittens - Google Search", title);
    }
    
    @After
    public void tearDown() {
        browser.quit();
    }
}
