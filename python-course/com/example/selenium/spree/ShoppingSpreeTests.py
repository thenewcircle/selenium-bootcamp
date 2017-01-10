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
        driver_type = DriverType.Chrome
    
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
            
    def testCapabilities(self):
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

    def testGetGoogleUrl(self):
        self.webDriver.get("http://google.com")

        title = self.webDriver.title
        self.assertEquals("Google", title)

        url = self.webDriver.current_url
        msg = "Found " + url
        self.assertTrue(url.startswith("https://www.google.com"), msg)
            
    def testNoComments(self):
        source = self.webDriver.page_source
        self.assertFalse("<!--" in source)

    def testHomePage(self):
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)
    
    def tearDown(self):
    
        directory = "\\tmp\\test-failures"
        if not os.path.exists(directory):
            os.makedirs(directory)
            
        file_name = directory + "\\ShoppingSpreeTests-" + self._testMethodName + ".png"
        self.webDriver.get_screenshot_as_file(file_name)
        
        self.webDriver.quit()
