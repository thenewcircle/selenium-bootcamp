package com.example.selenium.labs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.example.selenium.SeleniumUtils;

public abstract class AbstractPage {

	protected final WebDriver browser;

	public AbstractPage(WebDriver browser) {
		this.browser = browser;
	}

	public final ProductPage clickOnImageWithText(String text) {
	    // click on the image of the 'Ruby on Rails Tote', 1st product 1st row
	    // browser.findElement(By.cssSelector("li #product_1 > div.product-image > a > img[alt='Ruby on Rails Tote']")).click();
	    // browser.findElement(By.cssSelector("li #product_1 > div.product-image > a > img")).click();
	    browser.findElement(SeleniumUtils.imageWithAltText(text)).click();
	    return new ProductPage(browser);
	}

	public WebElement getHomeLink() {
		return browser.findElement(By.linkText("HOME"));
	}

	public ShopPage clickOnShopLinkInTopNavigationBar() {
	    // click on the 'SHOP' link at the top navigation bar
	    browser.findElement(By.cssSelector(".container > nav.columns.seven > #main-nav-bar > #shop-link > a[href='/products']")).click();
	    return new ShopPage(browser);
	}

	public void validateNavigationBarSytle() {
	    String color = getHomeLink().getCssValue("color");
	    Assert.assertEquals("rgba(0, 173, 238, 1)", color);
	
	    boolean enabled = getHomeLink().isEnabled();
	    Assert.assertTrue(enabled);
	}
}
