import os
import unittest
from enum import Enum

from selenium import webdriver

class DriverType(Enum):
    Chrome = 1
    Firefox = 2
    IE = 3
    Safari = 4
    Edge = 5

class ShoppingSpreeTests(unittest.TestCase):

    def setUp(self):
        driver_type = DriverType.Firefox
    
        if DriverType.Firefox == driver_type:
            path = os.environ["webdriver_firefox_marionette"]
            self.webDriver = webdriver.Firefox(executable_path=path)
        
        elif DriverType.Chrome == driver_type:
            path = os.environ["webdriver_chrome_driver"]
            self.webDriver = webdriver.Chrome(executable_path=path)
        
        elif DriverType.IE == driver_type:
            path = os.environ["webdriver_ie_driver"]
            self.webDriver = webdriver.Ie(executable_path=path)
        
        elif DriverType.Edge == driver_type:
            path = os.environ["webdriver_edge_driver"]
            self.webDriver = webdriver.Edge(executable_path=path)
        
        elif DriverType.Safari == driver_type:
            self.webDriver = webdriver.Safari()
            
    def testHomePage(self):
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)
    
    def tearDown(self):
        self.webDriver.quit()
