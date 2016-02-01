package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    Assert.assertEquals("Taxon", deptCmb.getAttribute("aria-label"));
    Assert.assertEquals("rgba(255, 255, 255, 1)",
        deptCmb.getCssValue("background-color"));

    Point location = deptCmb.getLocation();
    String msg = "Found " + location;
    Assert.assertTrue(msg, location.getX() > 100);
    Assert.assertTrue(msg, location.getY() < 200);

    Dimension size = deptCmb.getSize();
    msg = "Found " + size;
    Assert.assertTrue(msg, size.getWidth() > 100 && size.getWidth() < 120);
    Assert.assertTrue(msg, size.getHeight() > 15 && size.getHeight() < 25);

    Assert.assertEquals("select", deptCmb.getTagName());

    Assert.assertTrue(deptCmb.isDisplayed());
    Assert.assertFalse(deptCmb.isSelected());
    Assert.assertTrue(deptCmb.isEnabled());

    if (webDriver instanceof InternetExplorerDriver) {
      Assert.assertEquals("All departments Categories Brand", deptCmb.getText());
    } else {
      Assert.assertEquals("All departments\nCategories\nBrand", deptCmb.getText());
    }
  }

  public ProductsPage search(String keywords) {
    WebElement searchTF = webDriver.findElementById("keywords");
    searchTF.sendKeys(keywords);
    searchTF.submit();
    return new ProductsPage(webDriver);
  }

  public void clearSearch() {
    WebElement searchTF = webDriver.findElementById("keywords");
    searchTF.clear();
    Assert.assertEquals("", searchTF.getAttribute("value"));
  }

  public HomePage clickLogo() {
    webDriver.findElementById("logo").click();

    WebDriverWait wait = new WebDriverWait(webDriver, 30);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/"));

    return new HomePage(webDriver);
  }
}
