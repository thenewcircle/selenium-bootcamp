package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductsPage extends SpreePage {

    public ProductsPage(RemoteWebDriver webDriver) {
        super(webDriver);

        String url = "https://spreecommerce-demo.herokuapp.com/products";
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.urlContains(url));

        Assert.assertEquals(webDriver.getTitle(), "Spree Demo Site");
    }

    public ProductPage clickProductLnk(String name) {
        WebElement link = webDriver.findElementByLinkText(name);
        link.click();
        return new ProductPage(webDriver, name);
    }
}
