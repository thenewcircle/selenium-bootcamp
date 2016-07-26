package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
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

    public void validateImageSrc(String expectedUrl) {
        WebElement mainImg = webDriver.findElementByCssSelector("#main-image>img");
        String actualUrl = mainImg.getAttribute("src");
        Assert.assertEquals(actualUrl, expectedUrl);
    }

    public void clickThumbnail(int i) {
        String path = ".//*[@id='product-thumbnails']/li[" + i + "]/a/img";
        WebElement imgThmb = webDriver.findElementByXPath(path);
        imgThmb.click();
    }

    public void setQuantity(int q) {
        WebElement quantityTF = webDriver.findElementByName("quantity");
        quantityTF.sendKeys(""+q);
    }

    public CartPage clickAddToCart() {
        webDriver.findElementByTagName("button").click();
        return new CartPage(webDriver);
    }
}
