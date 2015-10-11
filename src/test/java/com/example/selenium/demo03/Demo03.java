package com.example.selenium.demo03;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Platform;
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
    }

    @Test
    public  void demoGetScreenshot() throws Exception {
        Assert.assertEquals(Platform.XP, Platform.getCurrent());
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
