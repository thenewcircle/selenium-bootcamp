package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class HomePage extends SpreePage {

    // RemoteWebDriver webDriver;

    public HomePage(RemoteWebDriver webDriver) {
        //this.webDriver = webDriver;
        super(webDriver);

        String url = "https://spreecommerce-demo.herokuapp.com/";
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.urlContains(url));
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

//    public void validateUrl() {
//        String url = webDriver.getCurrentUrl();
//        Assert.assertEquals(url, "https://spreecommerce-demo.herokuapp.com/");
//    }
}

