package com.example.selenium.demos;

import java.awt.Desktop;
import java.io.File;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Demo02 {

	FirefoxDriver browser;
    
    @Before
    public void setUp() throws Exception {
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().window().maximize();
    }

    @Test
    public void demoGetVersion() throws Exception {
    	Map<String,?> capabilities = browser.getCapabilities().asMap();

    	Assert.assertEquals(capabilities.get("applicationCacheEnabled"), true);
    	Assert.assertEquals(capabilities.get("rotatable"), false);
    	Assert.assertEquals(capabilities.get("handlesAlerts"), true);
    	Assert.assertEquals(capabilities.get("databaseEnabled"), true);
    	Assert.assertEquals(capabilities.get("version"), "41.0.1");
    	Assert.assertEquals(capabilities.get("platform"), Platform.WINDOWS);
    	Assert.assertEquals(capabilities.get("nativeEvents"), false);
    	Assert.assertEquals(capabilities.get("acceptSslCerts"), true);
    	Assert.assertEquals(capabilities.get("webStorageEnabled"), true);
    	Assert.assertEquals(capabilities.get("locationContextEnabled"), true);
    	Assert.assertEquals(capabilities.get("browserName"), "firefox");
    	Assert.assertEquals(capabilities.get("takesScreenshot"), true);
    	Assert.assertEquals(capabilities.get("javascriptEnabled"), true);
    	Assert.assertEquals(capabilities.get("cssSelectorsEnabled"), true);

    	System.out.println(capabilities);
    	System.out.flush();
    	Thread.sleep(1000);
    }
    
    @Test
    public  void demoGetTitle() throws Exception {
        browser.get("https://superb-store-8178.spree.mx/");
        Assert.assertEquals("Spree Demo Site", browser.getTitle());
        
        WebElement element = browser.findElement(By.tagName("body"));
        Assert.assertTrue(element.isDisplayed());
        
        Assert.assertTrue(true);
        Assert.assertEquals(1.2, 1.2, 0.01);
    }

    @Test
    public  void demoGetScreenshot() throws Exception {
        browser.get("https://superb-store-8178.spree.mx/");
        File file = browser.getScreenshotAs(OutputType.FILE);
        // need to copy file to some where safe.
        // IoUtils.copyFile(file, new File("c:\test\downloads"));
        // System.out.println(file);;
        // Desktop.getDesktop().open(file);
    }

    @Test
    public  void demoGetPlatform() throws Exception {
        Assert.assertEquals(Platform.XP, Platform.getCurrent());
    }
    
    @After
    public void tearDown() {
        browser.quit();
    }
    
    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }
}