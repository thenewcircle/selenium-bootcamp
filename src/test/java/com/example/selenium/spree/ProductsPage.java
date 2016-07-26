package com.example.selenium.spree;

import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProductsPage extends SpreePage {

    public ProductsPage(RemoteWebDriver webDriver) {
        super(webDriver, ExpectedConditions.urlToBe("http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag"));
    }

    public void validateUrl() {
        String url = webDriver.getCurrentUrl();
        Assert.assertEquals(url, "http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=Bag");
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }
}
