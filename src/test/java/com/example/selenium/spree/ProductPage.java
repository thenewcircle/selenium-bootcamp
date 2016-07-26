package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class ProductPage extends SpreePage {

    // private RemoteWebDriver webDriver;

    public ProductPage(RemoteWebDriver webDriver) {
        // this.webDriver = webDriver;
        super(webDriver);
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");
    }
}
