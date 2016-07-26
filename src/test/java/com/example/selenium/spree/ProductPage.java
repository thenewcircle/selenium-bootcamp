package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProductPage extends SpreePage {

    // private RemoteWebDriver webDriver;

    public ProductPage(RemoteWebDriver webDriver) {
        // this.webDriver = webDriver;
        super(webDriver, ExpectedConditions.urlToBe("http://spree.newcircle.com/products/spree-tote"));
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");
    }
}
