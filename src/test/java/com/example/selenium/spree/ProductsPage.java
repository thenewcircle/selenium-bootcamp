package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProductsPage extends SpreePage {

    public ProductsPage(RemoteWebDriver webDriver) {
        super(webDriver, ExpectedConditions.urlContains("http://spree.newcircle.com/products"));
    }

    public void validateUrl() {
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, "http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag");
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    public ProductPage clickProductLnk(String name) {
        WebElement prodLnk = webDriver.findElementByLinkText(name);
        prodLnk.click();
        return new ProductPage(webDriver);
    }
}
