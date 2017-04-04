import os
import unittest

from selenium import webdriver
from ddt import ddt, idata

from com.example.selenium.spree.pages import Pages

@ddt
class ShoppingSpreeTests(unittest.TestCase):
    
    # driver_types = ["chrome"]
    driver_types = ["chrome", "firefox", "ie"]

    def create_driver(self, driver_type):
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
        elif "safari" == driver_type:
            self.webDriver = webdriver.Safari()


    # def setUp(self):
        # path = os.environ["webdriver_firefox_driver"]
        # self.webDriver = webdriver.Firefox(executable_path=path)
        #
        # path = os.environ["webdriver_chrome_driver"]
        # self.webDriver = webdriver.Chrome(executable_path=path)
        #
        # path = os.environ["webdriver_ie_driver"]
        # self.webDriver = webdriver.Ie(executable_path=path)

    def testHomePage(self):
        self.create_driver("chrome")

        home_page = Pages.openHomePage(self)
        home_page.validateTitle()
        home_page.validateUrl()

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
        self.webDriver.get("http://www.google.com")
        source = self.webDriver.page_source
        self.assertFalse("<!--" in source)

    @idata(driver_types)
    def testCapabilities(self, driver_type):
        self.create_driver(driver_type)
        capabilities = self.webDriver.capabilities
        
        name = capabilities["browserName"]
        version = capabilities["version"]
        platform = capabilities["platform"]
        js = capabilities["javascriptEnabled"]

        if "chrome" == name:
            self.assertEquals("Windows NT", platform)
            # self.assertEquals("56.0.2924.87", version)
            self.assertTrue(js)
        elif "firefox" == name:
            self.assertEquals("WINDOWS_NT", platform)
            # self.assertEquals("56.0.2924.87", version)
            self.assertTrue(js)
        elif "internet explorer" == name:
            self.assertEquals("WINDOWS", platform)
            # self.assertEquals("56.0.2924.87", version)
            self.assertTrue(js)
        else:
            self.assertEquals("X", name)

    def testCartPage(self):
        self.create_driver("chrome")

        cart_page = Pages.openCartPage(self)
        cart_page.validateTitle()
        cart_page.validateUrl()

    def testBackAndForth(self):
        self.create_driver("chrome")

        self.webDriver.get("https://selenium.jacobparr.com/products/spree-bag")
        title = self.webDriver.title
        self.assertEquals("Spree Bag - Spree Demo Site", title)

        self.webDriver.get("https://selenium.jacobparr.com/products/spree-tote")
        title = self.webDriver.title
        self.assertEquals("Spree Tote - Spree Demo Site", title)

        self.webDriver.back()
        title = self.webDriver.title
        self.assertEquals("Spree Bag - Spree Demo Site", title)

        self.webDriver.forward()
        title = self.webDriver.title
        self.assertEquals("Spree Tote - Spree Demo Site", title)

        self.webDriver.refresh()
        title = self.webDriver.title
        self.assertEquals("Spree Tote - Spree Demo Site", title)

    def tearDown(self):
        directory = "\\tmp\\test-failures"
        if not os.path.exists(directory):
            os.makedirs(directory)
        file_name = directory + "\\ShoppingSpreeTests-" + self._testMethodName + ".png"
        self.webDriver.get_screenshot_as_file(file_name)
        
        self.webDriver.quit()
