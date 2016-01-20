package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SpreePage {

  protected final RemoteWebDriver browser;

  public SpreePage(RemoteWebDriver browser) {
    this.browser = browser;
  }

  public void validateIeComments() {
    String src = browser.getPageSource();
    String msg = "Found " + src.substring(0, 5000);
    String expected = "<!--[if lt IE 9]>\n  <script src=\"//cdnjs.cloudflare.com/ajax/libs/html5shiv/3.6/html5shiv.min.js\"></script>\n<![endif]-->";
    boolean contained = src.contains(expected);
    Assert.assertTrue(msg, contained);
  }

  public WebElement getDepartmentCmb() {
    return browser.findElementById("taxon");
  }


  private WebElement getKeywordsTF() {
    return browser.findElementById("keywords");
  }
  
  public ProductsPage search(String keywords) {
    WebElement searchTF = getKeywordsTF();
    searchTF.clear();
    searchTF.sendKeys(keywords);
    searchTF.submit();
    return new ProductsPage(browser);
  }

  public void clearSearch() {
    WebElement searchTF = getKeywordsTF();
    searchTF.clear();
  }

  public HomePage clickLogo() {
    WebElement logo = browser.findElementByCssSelector("#logo > a");
    logo.click();

    WebDriverWait wait = new WebDriverWait(browser, 5, 1000);
    wait.until(ExpectedConditions.urlToBe("http://spree.newcircle.com/"));

    return new HomePage(browser);
  }

  public void validateCartLink(int count, String amount) {
    String LNKTXT = "CART: ";

    WebDriverWait wait = new WebDriverWait(browser, 5);
    By by = By.partialLinkText(LNKTXT);
    wait.until(ExpectedConditions.presenceOfElementLocated(by));
    
    WebElement cartLnk = browser.findElementByPartialLinkText(LNKTXT);
    // WebElement cartLnk = browser.findElementByCssSelector("a.cart-info");
    String text = cartLnk.getText();
  
    if (count == 0) {
      Assert.assertEquals("CART: (EMPTY)", text);
    } else {
      String expected = String.format("CART: (%s) $%s", count, amount);
      Assert.assertEquals(expected, text);
    }
  }

}
