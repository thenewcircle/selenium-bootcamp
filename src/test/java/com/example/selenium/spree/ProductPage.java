package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

public class ProductPage extends SpreePage {

    // private RemoteWebDriver webDriver;

    public ProductPage(RemoteWebDriver webDriver) {
        // this.webDriver = webDriver;
        super(webDriver, ExpectedConditions.urlContains("http://spree.newcircle.com/products/"));
        // super(webDriver, ExpectedConditions.urlContains("http://spree.newcircle.com/products/"+product));
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");
    }

    public void validateImageSrc(String expectedUrl) {
        WebElement mainImg = webDriver.findElementByCssSelector("#main-image>img");
        String actualUrl = mainImg.getAttribute("src");
        Assert.assertTrue(actualUrl.startsWith(expectedUrl), "Found " + actualUrl);
    }

    public void clickThumbnail(int i) {
        i = i + 1;
        String path = ".//*[@id='product-thumbnails']/li[" + i + "]/a/img";
        WebElement imgThmb = webDriver.findElementByXPath(path);
        imgThmb.click();
    }

    public void setQuantity(int q) {
        WebElement quantityTF = webDriver.findElementByName("quantity");
        quantityTF.clear();
        quantityTF.sendKeys(""+q);
    }

    public CartPage clickAddToCart() {
        webDriver.findElementByTagName("button").click();
        return new CartPage(webDriver);
    }
}
