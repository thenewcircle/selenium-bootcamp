package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public abstract class SpreePage {

  protected final RemoteWebDriver webDriver;
  
  public SpreePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
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
}

