package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CartPage extends SpreePage {

    public CartPage(RemoteWebDriver webDriver) {
        super(webDriver);

        String url = "https://spreecommerce-demo.herokuapp.com/cart";
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.urlContains(url));

        Assert.assertEquals(webDriver.getTitle(), "Shopping Cart - Spree Demo Site");
    }

    public ProductsPage clickContinueShopping() {
        WebElement continueLink = webDriver.findElementByLinkText("Continue shopping");
        continueLink.click();

        return new ProductsPage(webDriver);
    }
}
