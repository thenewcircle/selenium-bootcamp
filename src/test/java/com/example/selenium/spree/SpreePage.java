package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class SpreePage {

  protected final RemoteWebDriver webDriver;

  public SpreePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public void validateIeComments() {
    String content = webDriver.getPageSource();
    String msg = "Found: " + content.substring(0,  1000);
    Assert.assertTrue(msg, content.contains("<!--[if lt IE 9]>"));
  }

  public WebElement getDepartmentCmb() {
    return webDriver.findElementById("taxon");
  }

  public ProductsPage search(String text) {
    By by = By.id("keywords");
    WebElement searchTF = webDriver.findElement(by);
    
    searchTF.clear();
    searchTF.sendKeys(text);
//    searchTF.submit();
//    searchTF.sendKeys("\n");
    searchTF.sendKeys(Keys.ENTER);

//    ExpectedCondition<Boolean> expectation = ExpectedConditions.urlToBe("http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=bag");
//    new WebDriverWait(webDriver, 5, 1000).until(expectation);

    return new ProductsPage(webDriver);
  }

  public void clearSearch() {
    WebElement searchTF = webDriver.findElementById("keywords");
    searchTF.clear();

    String actual = searchTF.getAttribute("value");
    String expected = "";
    Assert.assertEquals(expected, actual);
  }

  public HomePage clickLogo() {
    WebElement logoImg = webDriver.findElementById("logo");
    logoImg.click();
    
    ExpectedCondition<Boolean> expectation = ExpectedConditions.urlToBe("http://spree.newcircle.com/");
    new WebDriverWait(webDriver, 30, 1000).until(expectation);
    
    return new HomePage(webDriver);
  }
}
