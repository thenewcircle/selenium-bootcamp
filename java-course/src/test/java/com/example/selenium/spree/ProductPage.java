package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

/**
 * Created by jacobp on 11/2/2016.
 */
public class ProductPage extends SpreePage {

//    RemoteWebDriver webDriver;

    public ProductPage(RemoteWebDriver webDriver) {
        // this.webDriver = webDriver;
        super(webDriver);
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");
    }

    public void validateUrl() {
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, "https://spreecommerce-demo.herokuapp.com/products/spree-tote");
    }
}
