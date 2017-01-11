import os
import unittest

from ddt import ddt, data, idata
from selenium import webdriver

driver_types = ["chrome", "firefox"]

@ddt
class ShoppingSpreeTests(unittest.TestCase):

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
            
    @idata(driver_types)
    def testHomePage(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)
    
    @idata(driver_types)
    def testA(self, driver_type):
        self.create_driver(driver_type)
        self.assertTrue(True)

    @idata(driver_types)
    def testB(self, driver_type):
        self.create_driver(driver_type)
        self.assertEquals(1, 1)
    
    def tearDown(self):
        self.webDriver.quit()
