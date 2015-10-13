package com.example.selenium.labs;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.example.selenium.SeleniumUtils;

public abstract class AbstractPage {

	protected final WebDriver browser;

	@FindBy(linkText="HOME") 
	private WebElement homeLink;
	
	public AbstractPage(WebDriver browser) {
		this.browser = browser;
	}

	public final void openNavigationMenu() {
		browser.findElement(By.xpath(".//*[@id='header']/div/nav[2]/a[1]/i")).click();
	}
	
	public final ProductPage clickOnImageWithText(String text) {
		// click on the image of the 'Ruby on Rails Tote', 1st product 1st row
	    // browser.findElement(By.cssSelector("li #product_1 > div.product-image > a > img[alt='Ruby on Rails Tote']")).click();
	    // browser.findElement(By.cssSelector("li #product_1 > div.product-image > a > img")).click();
	    browser.findElement(SeleniumUtils.imageWithAltText(text)).click();
	    return new ProductPage(browser);
	}

	public final WebElement getHomeLink() {
		openNavigationMenu();
		return homeLink;
		// return browser.findElement(By.linkText("HOME"));
	}

	public final WebElement getShopLink() {
		openNavigationMenu();
		return browser.findElement(By.linkText("SHOP"));
	}

	public final ShopPage clickOnShopLinkInTopNavigationBar() {
	    // click on the 'SHOP' link at the top navigation bar
	    //browser.findElement(By.cssSelector(".container > nav.columns.seven > #main-nav-bar > #shop-link > a[href='/products']")).click();

		// browser.findElement(By.cssSelector(".open-menu > .icon-menu")).click();
		
		getShopLink().click();
	    
	    
	    // return new ShopPage(browser);
	    return PageFactory.initElements(browser, ShopPage.class);
	}

	public final void validateNavigationBarSytle() {
	    String color = getHomeLink().getCssValue("color");
	    Assert.assertEquals("rgba(0, 173, 238, 1)", color);
	
	    boolean enabled = getHomeLink().isEnabled();
	    Assert.assertTrue(enabled);
	}
}
