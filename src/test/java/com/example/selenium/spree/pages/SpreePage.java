package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class SpreePage {

  protected final RemoteWebDriver webDriver;

  @FindBy(id = "taxon")
  private WebElement deptCmb;
  
  @FindBy(id = "keywords")
  private WebElement searchTF;

  @FindBy(id = "logo")
  private WebElement logoImg;

  @FindBy(className = "cart-info")
  private WebElement cartLnk;
  
  public SpreePage(RemoteWebDriver webDriver, String url) {
    this.webDriver = webDriver;
    PageFactory.initElements(webDriver, this);
    
    ExpectedCondition<Boolean> expectation = ExpectedConditions.urlContains(url);
    new WebDriverWait(webDriver, 5).until(expectation);
  }

  public void validateIeComments() {
    String content = webDriver.getPageSource();
    String msg = "Found: " + content.substring(0,  1000);
    Assert.assertTrue(msg, content.contains("<!--[if lt IE 9]>"));
  }

  public WebElement getDepartmentCmb() {
    return deptCmb;
  }

  public ProductsPage search(String text) {
    searchTF.clear();
    searchTF.sendKeys(text);
    searchTF.submit();

//    ExpectedCondition<Boolean> expectation = ExpectedConditions.urlToBe("http://spree.newcircle.com/products?utf8=%E2%9C%93&taxon=&keywords=bag");
//    new WebDriverWait(webDriver, 5, 1000).until(expectation);

    return new ProductsPage(webDriver);
  }

  public void clearSearch() {
    searchTF.clear();

    String actual = searchTF.getAttribute("value");
    String expected = "";
    Assert.assertEquals(expected, actual);
  }

  public HomePage clickLogo() {
    logoImg.click();
    
    ExpectedCondition<Boolean> expectation = ExpectedConditions.urlToBe("http://spree.newcircle.com/");
    new WebDriverWait(webDriver, 30, 1000).until(expectation);
    
    return new HomePage(webDriver);
  }

  public void validateCartLink(int quantity, String amount) {
    String text = cartLnk.getText();
    if (quantity == 0) {
      Assert.assertEquals("CART: (EMPTY)", text);
    } else {
      String expected = String.format("CART: (%s) $%s", quantity, amount);
      Assert.assertEquals(expected, text);
    }
  }
}
