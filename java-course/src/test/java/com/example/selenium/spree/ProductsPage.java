package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductsPage extends SpreePage {

    public ProductsPage(RemoteWebDriver webDriver, String keyword) {
        super(webDriver);

        String url = "https://spreecommerce-demo.herokuapp.com/products?utf8=%E2%9C%93&taxon=&keywords=" + keyword;
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.urlContains(url));
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Demo Site");
    }

    public ProductPage clickProductLnk(String name) {
        WebElement prodLnk = webDriver.findElementByLinkText(name);
        prodLnk.click();

        return new ProductPage(webDriver, name);
    }
}
