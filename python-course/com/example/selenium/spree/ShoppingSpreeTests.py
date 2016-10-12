import os
import unittest
from enum import Enum
from time import sleep

from selenium import webdriver
from selenium.webdriver.common.by import By
from selenium.webdriver.support import expected_conditions
from selenium.webdriver.support.wait import WebDriverWait

from com.example.selenium.spree.Pages import Pages
from com.example.selenium.spree.SpreePage import url_to_be


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
            # path = os.environ["webdriver_chrome_driver"]
            self.webDriver = webdriver.Chrome()
        elif DriverType.IE == driver_type:
            # path = os.environ["webdriver_ie_driver"]
            self.webDriver = webdriver.Ie()
        elif DriverType.Edge == driver_type:
            # path = os.environ["webdriver_edge_driver"]
            self.webDriver = webdriver.Edge()
        elif DriverType.Safari == driver_type:
            self.webDriver = webdriver.Safari()
            
        self.webDriver.implicitly_wait(5)

        
        self.webDriver.execute_script()
        self.webDriver.find_element(By.XPATH, ".//*[@id='product_1']/div/a/img")
        self.webDriver.find_element_by_xpath(".//*[@id='product_1']/div/a/img")

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
        cart_page = Pages.openCartPage(self)
        cart_page.validateTitle()
        cart_page.validateUrl()
        
    def testProductPage(self):
        prod_page = Pages.openProductPage(self)
        prod_page.validateTitle()
        prod_page.validateUrl()
        
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
        home_page = Pages.openHomePage(self)
        home_page.validateTitle()
        home_page.validateUrl()

    def testSearchSpree(self):
        homePage = Pages.openHomePage(self)
    
        productsPage = homePage.search("Bag")
        productsPage.validateUrl()
        productsPage.validateTitle()
    
        productsPage.validateSearchText("Bag")
        productsPage.clearSearch()
        productsPage.validateSearchText("")
    
        homePage = productsPage.clickLogo()

    def testDepartmentsCombo(self):
        homePage = Pages.openHomePage(self)
        deptCmb = homePage.getDepartmentCmb()

        attr = deptCmb.get_attribute("aria-label")
        self.assertEquals("Taxon", attr)
    
        color = deptCmb.value_of_css_property("background-color")
        self.assertEquals("rgba(255, 255, 255, 1)", color)
    
        location = deptCmb.location
        self.assertTrue(location["x"] > 100, "Actual " + str(location["x"]))
        self.assertTrue(location["y"] < 200, "Actual " + str(location["y"]))
    
        size = deptCmb.size
        self.assertTrue(size["width"] >= 100 & size["width"] <= 200, "Actual " + str(size["width"]))
        self.assertTrue(size["height"] >= 15 & size["height"] <= 25, "Actual " + str(size["height"]))
    
        tagName = deptCmb.tag_name
        self.assertEquals("select", tagName)
    
        displayed = deptCmb.is_displayed()
        self.assertTrue(displayed)
    
        selected = deptCmb.is_selected()
        self.assertFalse(selected)
    
        enabled = deptCmb.is_enabled()
        self.assertTrue(enabled)
    
        text = deptCmb.text
        self.assertEquals("All departments\nCategories\nBrand", text)

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
