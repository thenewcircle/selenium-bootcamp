from enum import Enum
from selenium import webdriver
import os
import unittest


class DriverType(Enum):
    Chrome = 1
    Firefox = 2
    IE = 3
    Safari = 4
    Edge = 5


class ShoppingSpreeTests(unittest.TestCase):
    
    def setUp(self):
        driver_type = DriverType.Chrome

        if DriverType.Firefox == driver_type:
            self.webDriver = webdriver.Firefox()
        elif DriverType.Chrome == driver_type:
            path = os.environ["webdriver_chrome_driver"]
            self.webDriver = webdriver.Chrome(path)
        elif DriverType.IE == driver_type:
            path = os.environ["webdriver_ie_driver"]
            self.webDriver = webdriver.Ie(path)
        elif DriverType.Edge == driver_type:
            path = os.environ["webdriver_edge_driver"]
            self.webDriver = webdriver.Edge()
        elif DriverType.Safari == driver_type:
            self.webDriver = webdriver.Safari()

    def testRefresh(self):
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-bag")
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.refresh()
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

    def testBackAndForth(self):
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-bag")
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)
        
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-tote")
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)

        self.webDriver.back()
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.forward()
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)

    def testCartPage(self):
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com/cart")

        title = self.webDriver.title
        self.assertEquals("Shopping Cart - Spree Demo Site", title)

        url = self.webDriver.current_url
        self.assertEquals("https://spreecommerce-demo.herokuapp.com/cart", url)

    def testCapabilities(self):
        capabilities = self.webDriver.capabilities

        name = capabilities["browserName"]

        if "chrome" == name:
            # Add chrome-specific assertion here...
            version = capabilities["version"]
            self.assertEquals("53.0.2785.143", version)
    
            platform = capabilities["platform"]
            self.assertEquals("Windows NT", platform)
            
            js = capabilities["javascriptEnabled"]
            self.assertEquals(True, js)

        elif "firefox" == name:
            version = capabilities["version"]
            self.assertEquals("47.0.1", version)
    
            platform = capabilities["platform"]
            self.assertEquals("WINDOWS", platform)
    
            js = capabilities["javascriptEnabled"]
            self.assertEquals(True, js)

        elif "internet explorer" == name:
            version = capabilities["version"]
            self.assertEquals("11", version)
        
            platform = capabilities["platform"]
            self.assertEquals("WINDOWS", platform)
        
            js = capabilities["javascriptEnabled"]
            self.assertEquals(True, js)

        else:
            msg = "The browser " + name + " is not supported."
            self.fail(msg)

    def testNoComments(self):
        self.webDriver.get("http://www.example.com")
        source = self.webDriver.page_source
        self.assertFalse("<!--" in source)

    def testGetGoogleUrl(self):
        self.webDriver.get("http://google.com")
        title = self.webDriver.title
        self.assertEquals("Google", title)
        url = self.webDriver.current_url
        msg = "Found " + url
        self.assertTrue(url.startswith("https://www.google.com/?"), msg)

    def testHomePage(self):
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)
        
    def testA(self):
        self.assertTrue(True)

    def testB(self):
        self.assertEquals(1, 1)

    def tearDown(self):
        directory = "\\tmp\\test-failures"
        if not os.path.exists(directory):
            os.makedirs(directory)
        
        file_name = directory + "\\ShoppingSpreeTests-" + self._testMethodName + ".png"
        self.webDriver.get_screenshot_as_file(file_name)
        self.webDriver.quit()
