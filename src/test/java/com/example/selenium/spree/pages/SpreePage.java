package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SpreePage {

  protected final RemoteWebDriver webDriver;

  @FindBy(id = "keywords")
  protected WebElement searchTF;

  @FindBy(id = "logo")
  protected WebElement logoImg;

  @FindBy(className = "cart-info")
  protected WebElement cartLnk;

  @FindBy(id="taxon")
  protected WebElement deptCmb;

  protected SpreePage(RemoteWebDriver webDriver) {
    this.webDriver = webDriver;

    PageFactory.initElements(webDriver, this);
  }

  public void validateDepartmentCmb() {
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
    searchTF.sendKeys(keywords);
    searchTF.submit();
    return new ProductsPage(webDriver);
  }

  public void clearSearch() {
    searchTF.clear();
    Assert.assertEquals("", searchTF.getAttribute("value"));
  }

  public HomePage clickLogo() {
    logoImg.click();
    return new HomePage(webDriver);
  }

  public void validateCartLink(int count, String amount) {
    if (count == 0) {
      Assert.assertEquals("CART: (EMPTY)", cartLnk.getText());
    } else {
      String expected = String.format("CART: (%s) $%s", count, amount);
      Assert.assertEquals(expected, cartLnk.getText());
    }
  }
}
