package com.example.selenium.spree;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class SpreePage {

    final RemoteWebDriver webDriver;

    @FindBy(id="keywords")
    WebElement searchTF;

    @FindBy(id="taxon")
    WebElement departmentCmb;

    final int DELAY = 5;

    public void leftClick(By by) {
        WebElement element = findElementBy(by);
        element.click();
    }

    public void rightClick(By by) {
        WebElement element = findElementBy(by);

        Actions action = new Actions(webDriver).contextClick(element);
        action.build().perform();
    }

    public WebElement findElementBy(By by) {
        return new WebDriverWait(webDriver, DELAY).until(ExpectedConditions.elementToBeClickable(by));
    }

    public SpreePage(RemoteWebDriver webDriver) {
        this.webDriver = webDriver;
        PageFactory.initElements(webDriver, this);
    }

    public WebElement getDepartmentCmb() {
        return departmentCmb;
    }

    public ProductsPage search(String keywords) {
        searchTF.sendKeys(keywords);
        searchTF.submit();
        return new ProductsPage(webDriver, keywords);
    }

    public void validateSearchText(String expected) {
        String text = searchTF.getAttribute("value");
        Assert.assertEquals(text, expected);
    }

    public void clearSearch() {
        searchTF.clear();
    }

    public HomePage clickLogo() {
        WebElement logo = webDriver.findElementByCssSelector("#logo a");
        logo.click();

        return new HomePage(webDriver);
    }

    public void validateCartLink(int quantity, String amount) {

        String expected;

        if (quantity == 0) {
            expected = "Cart: (Empty)";
        } else {
            expected = String.format("Cart: (%s) $%s", quantity, amount);
        }

//        WebElement quantityTF = new WebDriverWait(webDriver, 5).until(
//                ExpectedConditions.presenceOfElementLocated(By.partialLinkText("Cart: "))
//        );

            WebElement quantityTF = new WebDriverWait(webDriver, 5).until(
                    ExpectedConditions.presenceOfElementLocated(By.linkText(expected))
    );

//        WebElement quantityTF = new WebDriverWait(webDriver, 5).until(
//                ExpectedConditions.textToBe(By.partialLinkText("Cart: "), ex)
//        );


        String actual = quantityTF.getText();
        Assert.assertEquals(actual, expected);
    }
}











