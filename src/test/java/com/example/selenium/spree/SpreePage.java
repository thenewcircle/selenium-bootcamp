package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class SpreePage {

  protected final RemoteWebDriver webDriver;

  public SpreePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;
  }

  public WebElement getDepartmentCmb() {
    return webDriver.findElementById("taxon");
  }

  public void validateDepartmentCmb() {
    WebElement deptCmb = getDepartmentCmb();
  
    String attribute = deptCmb.getAttribute("aria-label");
    Assert.assertEquals("Taxon", attribute);
    
    String cssValue = deptCmb.getCssValue("height");
    String name = webDriver.getCapabilities().getBrowserName();
  
    if ("firefox".equals(name)) {
      Assert.assertEquals("20px", cssValue);
    } else if ("internet explorer".equals(name)) {
      Assert.assertEquals("15.79px", cssValue);
    } else if ("chrome".equals(name)) {
      Assert.assertEquals("17px", cssValue);
    } else {
      throw new UnsupportedOperationException();
    }
    
    Point location = deptCmb.getLocation();
    Assert.assertTrue(location.getX() > 100);
    Assert.assertTrue(location.getY() < 200);
    
    Dimension size = deptCmb.getSize();
    Assert.assertTrue(size.getWidth() > 100 && size.getWidth() < 120);
    Assert.assertTrue(size.getHeight() > 15 && size.getHeight() < 25);
    
    String tagName = deptCmb.getTagName();
    Assert.assertEquals("select", tagName);
    
    Assert.assertTrue(deptCmb.isDisplayed());
    Assert.assertFalse(deptCmb.isSelected());
    Assert.assertTrue(deptCmb.isEnabled());
  
    String text = deptCmb.getText();
    if ("internet explorer".equals(name)) {
      Assert.assertEquals("All departments Categories Brand", text);
    } else {
      Assert.assertEquals("All departments\nCategories\nBrand", text);
    }
  }

}
