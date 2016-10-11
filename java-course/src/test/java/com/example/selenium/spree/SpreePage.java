package com.example.selenium.spree;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SpreePage {

    RemoteWebDriver webDriver;

    public SpreePage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getDepartmentCmb() {
        return webDriver.findElementById("taxon");
    }

    public ProductsPage search(String keywords) {
        WebElement searchTF=webDriver.findElementById("keywords");
        searchTF.sendKeys(keywords);
        searchTF.submit();
        return new ProductsPage(webDriver);
    }

    public void validateSearchText(String expected) {
        WebElement searchTF = webDriver.findElementById("keywords");
        String actual = searchTF.getAttribute("value");
        Assert.assertEquals(actual, expected);
    }

    public void clearSearch() {
        WebElement searchTF = webDriver.findElementById("keywords");
        searchTF.clear();
    }

    public HomePage clickLogo() {
        WebElement logo = webDriver.findElementById("logo");
        logo = logo.findElement(By.tagName("a"));
        logo.click();
        return new HomePage(webDriver);
    }

    public void validateCartLink(int quantity, String amount) {

        String expected;
        By by = By.partialLinkText("Cart: ");
        WebDriverWait wait = new WebDriverWait(webDriver, 5);

        if (quantity == 0) {
            expected = "Cart: (Empty)";
        } else {
            expected = String.format("Cart: (%s) $%s", quantity, amount);
        }

        wait.until(ExpectedConditions.textToBe(by, expected));
    }

    public void confirmResponse(String expected) {
        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        By by = By.className("alert-success");
        wait.until(ExpectedConditions.textToBe(by, expected));
    }

    public CartPage clickShoppingCart() {
        WebElement cartLnk = webDriver.findElementByPartialLinkText("Cart: ");
        cartLnk.click();
        return new CartPage(webDriver);
    }
}
