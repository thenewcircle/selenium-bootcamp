package com.example.selenium.labs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FeaturedPage extends AbstractPage {

	public FeaturedPage(WebDriver browser) {
		super(browser);
	}

	public void checkPriceRange(String string) {
        // make sure checkbox '$15.00-$18.00' in 'Price Range' is selected
        if (!browser.findElement(By.id(string)).isSelected()) {
            browser.findElement(By.id(string)).click();
        }
	}

	public void clickOnSearchButton() {
        // click on 'Search' button
        // browser.findElement(By.name("button")).click();
        browser.findElement(By.cssSelector("#sidebar_products_search>button[type='submit']")).click();
	}

	public CartPage clickOnCartLink() {
        // click on 'Cart' button in top right corner
        browser.findElement(By.cssSelector("a.cart-info.full")).click();
        return new CartPage(browser);
	}
}
