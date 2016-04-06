package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpreePage {

  protected final RemoteWebDriver webDriver;

  @FindBy(id="keywords")
  protected WebElement searchTF;

  @FindBy(id="taxon")
  protected WebElement departmentCmb;

  @FindBy(id="logo")
  WebElement logo;
  
  public SpreePage(RemoteWebDriver webDriver, String url, String title) {
    this.webDriver = webDriver;

    PageFactory.initElements(webDriver, this);
    
    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    wait.until(ExpectedConditions.urlContains(url));

    Assert.assertEquals(title, webDriver.getTitle());
  }

  public WebElement getDepartmentCmb() {
    return departmentCmb;
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

  public void clearSearch() {
    searchTF.clear();
  }

  public HomePage clickLogo() {
    logo.click();

    return new HomePage(webDriver);
  }

  public ProductsPage search(String text) {
    searchTF.sendKeys(text);
    searchTF.submit();

    return new ProductsPage(webDriver);
  }

  public void validateCartLink(int count, String amount) {
    String expected;
    if (count == 0) {
      expected = "CART: (EMPTY)";
    } else {
      expected = String.format("CART: (%s) $%s", count, amount);
    }

    By by = By.partialLinkText("CART: ");
    WebDriverWait wait = new WebDriverWait(webDriver, 5);
    WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(by));
    
    String actual = cartLink.getText();
    Assert.assertEquals(expected, actual);

  }
}
