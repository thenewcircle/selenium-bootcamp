package com.example.selenium.labs;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ShopPage extends AbstractPage {

	public ShopPage(WebDriver browser) {
		super(browser);
	}

	public BrandPage clickOnSpreeLinkInSidebarUnderShopByBrandSection() {
        // click on 'Spree' link in the sidebar under 'Shop by Brand' section
        browser.findElement(By.xpath("//aside[@id='sidebar']//a[.='Spree']")).click();
        return new BrandPage(browser);
	}
}
