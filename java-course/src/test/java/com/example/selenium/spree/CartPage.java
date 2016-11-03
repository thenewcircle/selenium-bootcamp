package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

/**
 * Created by jacobp on 11/2/2016.
 */
public class CartPage extends SpreePage {

//    RemoteWebDriver webDriver;

    public CartPage(RemoteWebDriver webDriver) {
        // this.webDriver = webDriver;
        super(webDriver);
    }

    public void validateUrl() {
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, "https://spreecommerce-demo.herokuapp.com/cart");
    }

    public void validateTitle() {
       String title = webDriver.getTitle();
        Assert.assertEquals(title, "Shopping Cart - Spree Demo Site");
    }
}
