package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends SpreePage {


    public HomePage(RemoteWebDriver webDriver) {
        super(webDriver);

        String url = "https://spreecommerce-demo.herokuapp.com/";
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.urlContains(url));

        Assert.assertEquals(webDriver.getTitle(), "Spree Demo Site");
    }
}
