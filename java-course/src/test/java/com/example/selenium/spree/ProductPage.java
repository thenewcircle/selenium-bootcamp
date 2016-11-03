package com.example.selenium.spree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ProductPage extends SpreePage {

    public ProductPage(RemoteWebDriver webDriver, String prodName) {
        // this.webDriver = webDriver;
        super(webDriver);
        String url = getUrlFromName(prodName);
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.urlContains(url));
    }

    public static String getUrlFromName(String prodName) {
        // Spree Mug > spree-mug
        // Jr. Tee > jr-tee
        String name = prodName.toLowerCase().replace(" ", "-").replace(".", "");

        return "https://spreecommerce-demo.herokuapp.com/products/" + name;
    }

    public void validateTitle() {
        String title = webDriver.getTitle();
        Assert.assertEquals(title, "Spree Tote - Spree Demo Site");
    }

    public void validateImageSrc(String expected) {
        WebElement element = webDriver.findElementByCssSelector("#main-image > div > img");
        String actual = element.getAttribute("src");
        Assert.assertTrue(actual.startsWith(expected), "Found " + actual);
    }

    public void clickThumbnail(int index) {
        index += 1;
        String path = ".//*[@id='product-thumbnails']/li[" + index + "]/a/img";
        WebElement thumbnail = webDriver.findElementByXPath(path);
        thumbnail.click();
    }

    public void setQuantity(int quantity) {
        WebElement quantityTF = webDriver.findElementByName("quantity");
        quantityTF.clear();
        quantityTF.sendKeys(quantity+"");
    }

    public void clickAddToCart() {
        WebElement addBtn = webDriver.findElementByTagName("button");
        addBtn.click();
    }

    public void confirmResponse(String expected) {
        By by = By.className("alert-success");
        new WebDriverWait(webDriver, 5).until(ExpectedConditions.textToBe(by, expected));
    }
}








