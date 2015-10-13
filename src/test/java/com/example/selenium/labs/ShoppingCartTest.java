package com.example.selenium.labs;

import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.example.selenium.SeleniumUtils;

@RunWith(Parameterized.class)
public class ShoppingCartTest {

	private enum DriverType { FireFox, Chome }
	
	private WebDriver browser;

    private final int width;
    private final int height;
    
    @Parameters
    public static Collection<Object[]> data() {

    	return Arrays.asList(new Object[][] {     
//            { DriverType.FireFox, 700, 600 }, 
            { DriverType.FireFox, 1024, 600 },
            
//            { DriverType.Chome, 700, 600 }, 
//            { DriverType.Chome, 1024, 600 }
           });
    }
    
    public ShoppingCartTest(DriverType driverType, int width, int height) {
		super();
		this.width = width;
		this.height = height;

		if (DriverType.FireFox == driverType) {
	    	browser = new FirefoxDriver();
		} else {
	    	System.getProperties().setProperty("webdriver.chrome.driver", "C:\\tools\\selenium\\chromedriver.exe");
	    	browser = new ChromeDriver();
		}
    }

	@Before
    public void setUp() throws Exception {
//    	Capabilities desiredCapabilities = new Capabilities();
//    	browser = new RemoteWebDriver(new URL("http://hub.com"), desiredCapabilities));
    	
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        // browser.manage().window().maximize();
         browser.manage().window().setPosition(new Point(0,0));
         browser.manage().window().setSize(new Dimension(width, height));
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
