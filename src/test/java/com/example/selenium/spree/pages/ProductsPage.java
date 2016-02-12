package com.example.selenium.spree.pages;

import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProductsPage extends SpreePage {

  public ProductsPage(RemoteWebDriver webDriver) {
    super(webDriver, "http://spree.newcircle.com/products");
  }

  public void validateTitle() {
    String actual = webDriver.getTitle();
    String expected = "Spree Demo Site";
    Assert.assertEquals(expected, actual);
  }

  public ProductPage clickProductLnk(final String name) throws Exception {
    String selector = "a[title='"+name+"']";
    
    webDriver.findElementByCssSelector(selector).click();

    String product = name.replace(" ", "-").toLowerCase();
    ExpectedCondition<Boolean> expectation = ExpectedConditions
        .urlToBe("http://spree.newcircle.com/products/"+product);
    new WebDriverWait(webDriver, 10).until(expectation);
    
    return new ProductPage(webDriver, name);
  }
}
