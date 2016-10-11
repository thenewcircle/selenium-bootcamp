package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductPage extends SpreePage {

    @FindBy(css="#main-image > div > img")
    WebElement mainImg;

    public static String getUrl(String productName) {
        String name = productName.toLowerCase().replace(".","-").replace(" ","-");
        return "https://spreecommerce-demo.herokuapp.com/products/" + name;
    }

    public ProductPage(RemoteWebDriver webDriver, String productName) {
        super(webDriver);

        String url = getUrl(productName);
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.urlContains(url));

        Assert.assertEquals(webDriver.getTitle(), productName + " - Spree Demo Site");
    }

    public void validateImageSrc(String expected) {
        String actual = mainImg.getAttribute("src");
        Assert.assertTrue(actual.startsWith(expected), "Found " + actual);
    }

    public void clickThumbnail(int index) {
        index++;
        String path = ".//*[@id='product-thumbnails']/li[" + index + "]/a/img";
        WebElement thumb = webDriver.findElementByXPath(path);
        thumb.click();
    }

    public void setQuantity(int amount) {
        WebElement quantityTF = webDriver.findElementByName("quantity");
        quantityTF.clear();
        quantityTF.sendKeys(""+amount);
    }

    public void clickAddToCart() {
        WebElement addBtn = webDriver.findElementByTagName("button");
        addBtn.click();
    }
}
