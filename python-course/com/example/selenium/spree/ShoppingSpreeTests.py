import os
import unittest
from enum import Enum

import time
from selenium import webdriver

from com.example.selenium.spree.pages import Pages


class DriverType(Enum):
    Chrome = 1
    Firefox = 2
    IE = 3
    Safari = 4
    Edge = 5
    Grid = 6

class ShoppingSpreeTests(unittest.TestCase):

    def setUp(self):
        driver_type = DriverType.Chrome

        self.is_firefox = False
        self.is_chrome = False
        self.is_ie = False

        if DriverType.Grid == driver_type:
            self.webDriver = webdriver.Remote(command_executor = "http://localhost:4444/wd/hub",
                                      desired_capabilities = {
                                          "browserName": "chrome"
                                      })
        elif DriverType.Firefox == driver_type:
            self.webDriver = webdriver.Firefox()
            self.is_firefox = True
        elif DriverType.Chrome == driver_type:
            path = os.environ["webdriver_chrome_driver"]
            self.webDriver = webdriver.Chrome(path)
            self.is_chrome = True
        elif DriverType.IE == driver_type:
            path = os.environ["webdriver_ie_driver"]
            self.webDriver = webdriver.Ie(path)
            self.is_ie = True
            self.webDriver.delete_all_cookies()
        elif DriverType.Edge == driver_type:
            path = os.environ["webdriver_edge_driver"]
            self.webDriver = webdriver.Edge()
        elif DriverType.Safari == driver_type:
            self.webDriver = webdriver.Safari()
            
        self.webDriver.maximize_window()
        self.webDriver.implicitly_wait(5)
        # self.webDriver.set_window_size(300, 200)
        
    def testShoppingSpree(self):
        homePage = Pages.openHomePage(self)
        productsPage = homePage.search("Mug")
        productPage = productsPage.clickProductLink("Spree Mug")
        productPage.validateImageSrc("https://spreecommerce-demo.herokuapp.com/spree/products/45/product/spree_mug.jpeg?")
        productPage.clickThumbnail(1)
        productPage.validateImageSrc("https://spreecommerce-demo.herokuapp.com/spree/products/46/product/spree_mug_back.jpeg?")
        productPage.setQuantity(3)
        productPage.validateCartLink(0, "")
        productPage.clickAddToCart()
        productPage.validateCartLink(3, "41.97")
        
        cartPage = productPage.clickShoppingCart()

        homePage = cartPage.clickLogo()
        # homePage.validateUrl()
        homePage.validateTitle()

    def testBackAndForth(self):
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-bag")
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-tote")
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)

        self.webDriver.back()
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.forward()
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)

        self.webDriver.refresh()
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)

    def testCapabilities(self):
        capabilities = self.webDriver.capabilities

        name = capabilities["browserName"]
        if name == "chrome":
            version = capabilities["version"]
            self.assertEquals("53.0.2785.143", version)
    
            platform = capabilities["platform"]
            self.assertEquals("Windows NT", platform)
    
            js = capabilities["javascriptEnabled"]
            self.assertTrue(js)

        elif name == "firefox":
            version = capabilities["version"]
            self.assertEquals("47.0.1", version)
    
            platform = capabilities["platform"]
            self.assertEquals("WINDOWS", platform)
    
            js = capabilities["javascriptEnabled"]
            self.assertTrue(js)

        else:
            self.fail("The browser " + name + " is not supported")

    def testSearchSpree(self):
        homePage = Pages.openHomePage(self)
    
        productsPage = homePage.search("Bag")
        productsPage.validateUrl()
        productsPage.validateTitle()

        productsPage.validateSearchText("Bag")
        productsPage.clearSearch()
        productsPage.validateSearchText("")

        homePage = productsPage.clickLogo()

        # homePage.validateUrl()

    def testDepartmentsCombo(self):
        home_page = Pages.openHomePage(self)
        deptCmb = home_page.getDepartmentCmb()

        attr = deptCmb.get_attribute("aria-label")
        self.assertEquals("Taxon", attr)
        
        color = deptCmb.value_of_css_property("background-color")
        self.assertEquals("rgba(255, 255, 255, 1)", color)

        location = deptCmb.location
        self.assertTrue(location["x"] > 100, "Found " + str(location["x"]))
        self.assertTrue(location["y"] < 200, "Found " + str(location["y"]))

        size = deptCmb.size
        self.assertTrue(size["width"] >= 100 and size["width"] <= 200, "Found " + str(size["width"]))
        self.assertTrue(size["height"] >= 15 and size["height"] <= 25, "Found " + str(size["height"]))
        
        tagName = deptCmb.tag_name
        self.assertEquals("select", tagName)

        displayed = deptCmb.is_displayed()
        self.assertTrue(displayed)

        selected = deptCmb.is_selected()
        self.assertFalse(selected)

        enabled = deptCmb.is_enabled()
        self.assertTrue(enabled)
        
        text = deptCmb.text
        # if ("internet explorer" == self.webDriver.capabilities["browserName"]):
        #     self.assertEquals("All departments Categories Brand", text)
        # else:
        #     self.assertEquals("All departments\nCategories\nBrand", text)

        if self.is_ie:
            self.assertEquals("All departments Categories Brand", text)
        else:
            self.assertEquals("All departments\nCategories\nBrand", text)


    def testProductPage(self):
        prod_page = Pages.openProductPage(self)
        prod_page.validateUrl()
        prod_page.validateTitle()

    def testCartPage(self):
        cart_page = Pages.openCartPage(self)
        cart_page.validateTitle()
        cart_page.validateUrl()

    def testHomePage(self):
        home_page = Pages.openHomePage(self)
        home_page.validateUrl()
        home_page.validateTitle()

    def testNoComments(self):
        self.webDriver.get("http://www.google.com")
        source = self.webDriver.page_source
        msg = "something useful"
        self.assertFalse("<!--" in source, msg)
            
    def testGetGoogleUrl(self):
        self.webDriver.get("http://google.com")
        
        title = self.webDriver.title
        self.assertEquals("Google", title)
        
        url = self.webDriver.current_url
        msg = "Found " + url
        self.assertTrue(url.startswith("https://www.google.com/?"), msg)
            
    def testA(self):
        self.assertTrue(True)

    def tesetB(self):
        self.assertEquals(1, 2)

    def tearDown(self):
        directory = "\\tmp\\test-failures"

        if not os.path.exists(directory):
            os.makedirs(directory)
            
        file_name = directory + "\\ShoppingSpreeTests-" + self._testMethodName + ".png"
        self.webDriver.get_screenshot_as_file(file_name)

        self.webDriver.quit()
        
