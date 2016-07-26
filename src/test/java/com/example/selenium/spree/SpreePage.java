package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class SpreePage {

    RemoteWebDriver webDriver;

    public SpreePage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public WebElement getDepartmentCmb() {
        return webDriver.findElementById("taxon");
    }

    public WebElement getSearchTF() {
        return webDriver.findElementById("keywords");
    }

    public ProductsPage search(String keywords) {
        getSearchTF().sendKeys(keywords);
        getSearchTF().submit();
        return new ProductsPage(webDriver);
    }

    public void validateSearchText(String expected) {
        String actual = getSearchTF().getAttribute("value");
        Assert.assertEquals(actual, expected);
    }

    public void clearSearch() {
        getSearchTF().clear();
    }

    public HomePage clickLogo() {
        WebElement logo = webDriver.findElementById("logo");
        logo.click();

        return new HomePage(webDriver);
    }
}
