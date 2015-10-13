package com.example.selenium.labs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CartPage extends AbstractPage {

	public CartPage(WebDriver browser) {
		super(browser);
	}

	public ShopPage clickContinueShoppingButton() {
        // click 'Continue Shopping' button
        browser.findElement(By.cssSelector(".continue.button.gray")).click();
        return new ShopPage(browser);
	}

	public String getTotal() {
		return browser.findElement(By.xpath("//tr[@class='cart-total']/td[2]/h5")).getText();
	}
}
