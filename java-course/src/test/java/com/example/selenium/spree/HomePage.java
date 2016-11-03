package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class HomePage extends SpreePage {

    // RemoteWebDriver webDriver;

    public HomePage(RemoteWebDriver webDriver) {
        //this.webDriver = webDriver;
        super(webDriver);
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    public void validateUrl() {
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, "https://spreecommerce-demo.herokuapp.com/");
    }
}

