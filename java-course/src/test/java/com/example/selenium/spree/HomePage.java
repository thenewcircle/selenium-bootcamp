package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class HomePage {
    public static final String URL = "https://spreecommerce-demo.herokuapp.com/";
    public static final String TITLE = "Spree Demo Site";

    private RemoteWebDriver webDriver;

    public HomePage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, TITLE);
    }

    public void validateUrl() {
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, URL);
    }
}