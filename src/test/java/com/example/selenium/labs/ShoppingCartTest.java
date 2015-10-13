package com.example.selenium.labs;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.example.selenium.SeleniumUtils;

public class ShoppingCartTest {

    private FirefoxDriver browser;

    @Before
    public void setUp() throws Exception {
        browser = new FirefoxDriver();
        browser.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
    }

    @Test
    public void testShoppingCart() {

        // open shopping page in firefox
        browser.navigate().to("https://XXXX-YYYY-DDDD.spree.mx/");

        // click on the 'SHOP' link at the top navigation bar
        browser.findElement(By.cssSelector(".container > nav.columns.seven > #main-nav-bar > #shop-link > a[href='/products']")).click();

        // click on the image of the 'Ruby on Rails Tote', 1st product 1st row
        // browser.findElement(By.cssSelector("li #product_1 > div.product-image > a > img[alt='Ruby on Rails Tote']")).click();
        // browser.findElement(By.cssSelector("li #product_1 > div.product-image > a > img")).click();
        browser.findElement(SeleniumUtils.imageWithAltText("Ruby on Rails Tote")).click();

        // click on the add to cart button
        browser.findElement(By.id("add-to-cart-button")).click();

        // click 'Continue Shopping' button
        browser.findElement(By.cssSelector(".continue.button.gray")).click();

        // click on 'Spree' link in the sidebar under 'Shop by Brand' section
        browser.findElement(By.xpath("//aside[@id='sidebar']//a[.='Spree']")).click();

        // click on the 'Spree Ringer T-Shirt' image
        // browser.findElement(By.cssSelector("li #product_10 > div.product-image > a > img[alt='Spree Ringer T-Shirt']")).click();
        browser.findElement(SeleniumUtils.imageWithAltText("Spree Ringer T-Shirt")).click();

        // click on 'Add to Cart' button
        browser.findElement(By.id("add-to-cart-button")).click();

        // verify that the actual total after adding to shopping cart is $35.98
        SeleniumUtils.contains("$35.98", browser.findElement(By.xpath("//tr[@class='cart-total']/td[2]/h5")).getText());

        // click on 'Continue Shopping' button
        browser.findElement(By.cssSelector(".continue.button.gray")).click();

        // click on the image of the 'Ruby on Rails Tote', 1st product 1st row
        // browser.findElement(By.cssSelector("li #product_1 > div.product-image > a > img[alt='Ruby on Rails Tote']")).click();
        browser.findElement(SeleniumUtils.imageWithAltText("Ruby on Rails Tote")).click();

        // click on 'Featured' under 'Look for Similar Items'
        // browser.findElement(By.linkText("Featured")).click();
        browser.findElement(By.cssSelector("#similar_items_by_taxon > li > a[href='/t/featured']")).click();

        // make sure checkbox '$15.00-$18.00' in 'Price Range' is selected
        if (!browser.findElement(By.id("Price_Range_$15.00_-_$18.00")).isSelected()) {
            browser.findElement(By.id("Price_Range_$15.00_-_$18.00")).click();
        }

        // click on 'Search' button
        // browser.findElement(By.name("button")).click();
        browser.findElement(By.cssSelector("#sidebar_products_search>button[type='submit']")).click();

        // click on 'Cart' button in top right corner
        browser.findElement(By.cssSelector("a.cart-info.full")).click();

        // verify that the actual total in cart after info page is $35.98
        SeleniumUtils.contains("$35.98", browser.findElement(By.xpath("//tr[@class='cart-total']/td[2]/h5")).getText());

    }

    @After
    public void tearDown() {
        browser.quit();
    }

    public static boolean isAlertPresent(FirefoxDriver wd) {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

}
