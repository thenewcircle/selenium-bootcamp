package com.example.selenium.spree;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SpreePage {

  RemoteWebDriver webDriver;

  public SpreePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
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
    String name = webDriver.getCapabilities().getPlatform().family().name();
    System.out.println("Platform is " + name);
    return "osx".equals(name);
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
}
