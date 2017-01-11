import os
import unittest
# from enum import Enum

from selenium import webdriver
from ddt import ddt, idata, data

from com.example.selenium.spree.pages import Pages

# driver_types = ["chrome", "firefox", "ie", "edge", "safari"]
driver_types = ["chrome"]

@ddt
class ShoppingSpreeTests(unittest.TestCase):

    def create_driver(self, driver_type):
        # driver_type = DriverType.Chrome
    
        if "firefox" == driver_type:
            path = os.environ["webdriver_firefox_driver"]
            self.webDriver = webdriver.Firefox(executable_path=path)
        
        elif "chrome" == driver_type:
            path = os.environ["webdriver_chrome_driver"]
            self.webDriver = webdriver.Chrome(executable_path=path)
        
        elif "ie" == driver_type:
            path = os.environ["webdriver_ie_driver"]
            self.webDriver = webdriver.Ie(executable_path=path)
        
        elif "edge" == driver_type:
            path = os.environ["webdriver_edge_driver"]
            self.webDriver = webdriver.Edge(executable_path=path)
        
        elif "safari"== driver_type:
            self.webDriver = webdriver.Safari()
            
    @idata(driver_types)
    def testBackAndForth(self, driver_type):
        self.create_driver(driver_type)
    
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

    @idata(driver_types)
    def testProductPage(self, driver_type):
        self.create_driver(driver_type)

        prod_page = Pages.openProductPage(self)
        prod_page.validateTitle()
        prod_page.validateUrl()

    @idata(driver_types)
    def testCartPage(self, driver_type):
        self.create_driver(driver_type)

        cart_page = Pages.openCartPage(self)
        cart_page.validateTitle()
        cart_page.validateUrl()

    @idata(driver_types)
    def testCapabilities(self, driver_type):
        self.create_driver(driver_type)

        capabilities = self.webDriver.capabilities

        name = capabilities["browserName"]
        if "firefox" == name:
            version = capabilities["browserVersion"]
            self.assertEquals("50.1.0", version)

            platform = capabilities["platform"]
            self.assertEquals("WINDOWS_NT", platform)
        
        elif "chrome" == name:
            version = capabilities["version"]
            self.assertEquals("55.0.2883.87", version)

            platform = capabilities["platform"]
            self.assertEquals("Windows NT", platform)
        
        elif "internet explorer" == name:
            pass
    
        js = capabilities["javascriptEnabled"]
        self.assertTrue(js)

    @idata(driver_types)
    def testGetGoogleUrl(self, driver_type):
        self.create_driver(driver_type)

        self.webDriver.get("http://google.com")

        title = self.webDriver.title
        self.assertEquals("Google", title)

        url = self.webDriver.current_url
        msg = "Found " + url
        self.assertTrue(url.startswith("https://www.google.com"), msg)
            
    @idata(driver_types)
    def testNoComments(self, driver_type):
        self.create_driver(driver_type)

        source = self.webDriver.page_source
        self.assertFalse("<!--" in source)

    @idata(driver_types)
    def testHomePage(self, driver_type):
        self.create_driver(driver_type)

        home_page = Pages.openHomePage(self)
        home_page.validateTitle()
        home_page.validateUrl()
        
        # self.webDriver.get("https://spreecommerce-demo.herokuapp.com")
        # title = self.webDriver.title
        # self.assertEquals("Spree Demo Site", title)
    
    def tearDown(self):
    
        directory = "\\tmp\\test-failures"
        if not os.path.exists(directory):
            os.makedirs(directory)
            
        file_name = directory + "\\ShoppingSpreeTests-" + self._testMethodName + ".png"
        self.webDriver.get_screenshot_as_file(file_name)
        
        self.webDriver.quit()
