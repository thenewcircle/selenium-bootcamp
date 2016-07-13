package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class SpreePage {

  protected final RemoteWebDriver webDriver;

  public SpreePage(RemoteWebDriver webDriver, ExpectedCondition<?> expectedCondition) {
    this.webDriver = webDriver;

    new WebDriverWait(webDriver, 15).until(expectedCondition);

    By by = By.partialLinkText("CART: ");
    new WebDriverWait(webDriver, 1).until(
        ExpectedConditions.elementToBeClickable(by));
  }

  public WebElement getDepartmentCmb() {
    // return webDriver.findElementById("taxon");

    WebElement deptCmb = webDriver.findElementById("taxon");
    return deptCmb;
  }

  public ProductsPage search(String text) {
    // Get the search text field
    WebElement searchTF = webDriver.findElementById("keywords");
    
    // search for ...
    searchTF.sendKeys(text);
    searchTF.submit();
    
    return new ProductsPage(webDriver);
  }

  public void clearSearch() {
    WebElement searchTF = webDriver.findElementById("keywords");
    searchTF.clear();
  }

  public HomePage clickLogo() {
    WebElement logo = webDriver.findElementById("logo");
    logo.click();
    return new HomePage(webDriver);
  }

  public void validateCartLink(int total, String amount) {
  
    WebElement cartLnk = webDriver.findElementByPartialLinkText("CART:");
    
    String expected;
    
    if (total == 0) {
      expected = "CART: (EMPTY)";
    } else {
      expected = String.format("CART: (%s) $%s", total, amount);
    }
  
    String actual = cartLnk.getText();
    Assert.assertEquals(expected, actual);
  }
}

