package com.example.selenium.labs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProductPage extends AbstractPage {

	public ProductPage(WebDriver browser) {
		super(browser);
	}

	public CartPage addProductToCart() {
        // click on the add to cart button
        browser.findElement(By.id("add-to-cart-button")).click();
        return new CartPage(browser);
	}

	public FeaturedPage clickOnFeaturedLink() {
        // click on 'Featured' under 'Look for Similar Items'
        // browser.findElement(By.linkText("Featured")).click();
        browser.findElement(By.cssSelector("#similar_items_by_taxon > li > a[href='/t/featured']")).click();
        return new FeaturedPage(browser);
	}
}
