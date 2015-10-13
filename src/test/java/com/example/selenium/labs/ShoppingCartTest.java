package com.example.selenium.labs;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.example.selenium.SeleniumUtils;

public class ShoppingCartTest {

    private WebDriver browser;

    @Before
    public void setUp() throws Exception {
//    	Capabilities desiredCapabilities = new Capabilities();
//    	browser = new RemoteWebDriver(new URL("http://hub.com"), desiredCapabilities));
    	
    	//System.getProperties().setProperty("webdriver.chrome.driver", "C:\\tools\\selenium\\chromedriver.exe");
//    	browser = new ChromeDriver();

//    	System.getProperties().setProperty("webdriver.ie.driver", "c:\\tools\\selenium\\IEDriverServer.exe");
//    	browser = new InternetExplorerDriver();

    	browser = new FirefoxDriver();
    	
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // browser.manage().window().maximize();
        browser.manage().window().setPosition(new Point(0,0));
        browser.manage().window().setSize(new Dimension(700, 700));
    }

    @Test 
    public void testVerifySytleOnHomePage() {
    	HomePage homePage = HomePage.open(browser);
        homePage.validateNavigationBarSytle();
    }

    @Test 
    public void testVerifySytleOnShopPage() {
    	HomePage homePage = HomePage.open(browser);
        ShopPage shopPage = homePage.clickOnShopLinkInTopNavigationBar();
        shopPage.validateNavigationBarSytle();
    }

    @Test
    public void testAdminLogin() {
    	HomePage homePage = HomePage.open(browser);
    	AdminPage adminPage = homePage.clickAdminLinkInTopNavigationBar();
    	
    	adminPage.setUsername("mickey.mouse@disney.com");
    	adminPage.setPassword("asdflkj");
    	adminPage.clickLoginButton();
    }
    
    @Test
    public void testShoppingCart() {
    	
    	HomePage homePage = HomePage.open(browser);
    	
        ShopPage shopPage = homePage.clickOnShopLinkInTopNavigationBar();

        ProductPage totePage = shopPage.clickOnImageWithText("Ruby on Rails Tote");

        CartPage cartPage = totePage.addProductToCart();
        
        shopPage = cartPage.clickContinueShoppingButton();
        
        BrandPage spreePage = shopPage.clickOnSpreeLinkInSidebarUnderShopByBrandSection(); 

        ProductPage spreeRingerTshirtPage = spreePage.clickOnImageWithText("Spree Ringer T-Shirt");
        
        cartPage = spreeRingerTshirtPage.addProductToCart();
        
        // verify that the actual total after adding to shopping cart is $35.98
        SeleniumUtils.contains("$35.98", cartPage.getTotal());

        shopPage = cartPage.clickContinueShoppingButton();
        
        totePage = shopPage.clickOnImageWithText("Ruby on Rails Tote");
        
        FeaturedPage featuredPage = totePage.clickOnFeaturedLink();
        
        featuredPage.checkPriceRange("Price_Range_$15.00_-_$18.00");
        featuredPage.clickOnSearchButton();

        cartPage = featuredPage.clickOnCartLink();
        
        SeleniumUtils.contains("$35.98", cartPage.getTotal());
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
