package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SpreePage {

    @FindBy(id = "keywords")
    WebElement searchTF;

    RemoteWebDriver webDriver;

    public SpreePage(RemoteWebDriver webDriver, ExpectedCondition condition) {
        this.webDriver = webDriver;

        WebDriverWait wait = new WebDriverWait(webDriver, 5);
        wait.until(condition);

        PageFactory.initElements(webDriver, this);
    }

    public WebElement getDepartmentCmb() {
        return webDriver.findElementById("taxon");
    }

//    public WebElement getSearchTF() {
//        return webDriver.findElement(By.id("keywords"));
//        // return webDriver.findElementById("keywords");
//    }

    public ProductsPage search(String keywords) {
        searchTF.sendKeys(keywords);
        searchTF.submit();
        return new ProductsPage(webDriver);
    }

    public void validateSearchText(String expected) {
        String actual = searchTF.getAttribute("value");
        Assert.assertEquals(actual, expected);
    }

    public void clearSearch() {
        searchTF.clear();
    }

    public HomePage clickLogo() {
        WebElement logo = webDriver.findElementById("logo");
        logo.click();

        return new HomePage(webDriver);
    }

    public void validateCartLink(int quantity, String amount) {
        WebElement cartLnk = webDriver.findElementByPartialLinkText("Cart: ");
        String text = cartLnk.getText();

        if (quantity == 0) {
            Assert.assertEquals(text, "Cart: (Empty)");

        } else {
            String actual = String.format("Cart: (%s) $%s", quantity, amount);
            Assert.assertEquals(text, actual);
        }

    }
}










