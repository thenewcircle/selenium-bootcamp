package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class CartPage extends SpreePage {

    // private RemoteWebDriver webDriver;

    public CartPage(RemoteWebDriver webDriver) {
        // this.webDriver = webDriver;
        super(webDriver, ExpectedConditions.urlToBe("http://spree.newcircle.com/cart"));
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Shopping Cart - Spree Demo Site");
    }

    public ProductsPage clickContinueShopping() {
        webDriver.findElementByLinkText("Continue shopping").click();
        return new ProductsPage(webDriver);
    }
}
