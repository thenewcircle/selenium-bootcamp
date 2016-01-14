package com.example.selenium.spree;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.selenium.spree.utils.RichWebDriver;

public class HomePage {

  private RichWebDriver browser;

  @FindBy(id="keywords")
  WebElement keywordsTF;

  @FindBy(css="#search-bar>form>input[type='submit']")
  WebElement searchBtn;
  
  public HomePage(RichWebDriver browser) {
    this.browser = browser;
    PageFactory.initElements(browser, this);
  }

  public static HomePage open(RichWebDriver browser) {
    browser.get("http://spree.newcircle.com");
    return new HomePage(browser);
  }

  public void assertUrl() {
    String url = browser.getCurrentUrl();
    Assert.assertEquals("http://spree.newcircle.com/", url);
  }

  public ProductsPage search(String string) {
    keywordsTF.sendKeys(string);
    searchBtn.click();
    
    return new ProductsPage(browser);
  }
}
