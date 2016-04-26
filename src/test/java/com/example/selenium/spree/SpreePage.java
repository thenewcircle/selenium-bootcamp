package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpreePage {

  RemoteWebDriver webDriver;

  public SpreePage(RemoteWebDriver webDriver,
                   String expectedUrl,
                   String expectedTitle) {
    this.webDriver = webDriver;
    
    PageFactory.initElements(webDriver, this);

//    String actualUrl = webDriver.getCurrentUrl();
//    String msg = "Found " + actualUrl;
//    Assert.assertTrue(msg, actualUrl.contains(expectedUrl));
    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    wait.until(ExpectedConditions.urlContains(expectedUrl));
    
    String actualTitle = webDriver.getTitle();
    Assert.assertEquals(expectedTitle, actualTitle);
  }

  public WebElement getDepartmentCmb() {
    return webDriver.findElementById("taxon");
  }

  public boolean isThisFirefox() {
    String name = webDriver.getCapabilities().getBrowserName();
    return "firefox".equals(name);
  }

  public boolean isThisIe() {
    String name = webDriver.getCapabilities().getBrowserName();
    return "internet explorer".equals(name);
  }

  public boolean isThisIeVersion6() {
    String name = webDriver.getCapabilities().getBrowserName();
    String version = webDriver.getCapabilities().getVersion();
    return "internet explorer".equals(name) && 
           "6".equals(version);
  }

  public boolean isMac() {
    String name = webDriver.getCapabilities().getPlatform().name();
    System.out.println("Platform is " + name);
    return "MAC".equals(name);
  }

  public void clearSearch() {
    WebElement searchTF = webDriver.findElementById("keywords");
    searchTF.clear();
  
    // String text = searchTF.getAttribute("value");
    // Assert.assertEquals("", text);
  }

  public ProductsPage search(String text) {
    WebElement searchTF = webDriver.findElementById("keywords");
    searchTF.sendKeys(text);
    searchTF.submit();
    
    return new ProductsPage(webDriver);
  }

  public HomePage clickLogo() {
    WebElement logo = webDriver.findElementById("logo");
    logo.click();
    
    return new HomePage(webDriver);
  }

  public void validateCartLink(int quantity, String amount) {
    WebElement cartLnk = webDriver.findElementByPartialLinkText("CART:");
    String actual = cartLnk.getText();
    
    if (quantity == 0) {
      String expected = "CART: (EMPTY)";
      Assert.assertEquals(expected, actual);
      
    } else {
      String expected = "CART: (" + quantity + ") $" + amount;
      Assert.assertEquals(expected, actual);
      
    }
  }
}
