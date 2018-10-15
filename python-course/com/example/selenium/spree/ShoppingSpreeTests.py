import os
import unittest

from selenium import webdriver
from ddt import ddt, idata


@ddt
class ShoppingSpreeTests(unittest.TestCase):
    driver_types = ["chrome", "firefox", "safari"]

    @classmethod
    def setUpClass(cls):
        print("Before class")

    def setUp(self):
        print("Before method")

    def create_driver(self, driver_type):
        if "firefox" == driver_type:
            # plug this in the .bash_profile file
            # export GECKO_DRIVER=/Users/$USER/geckodriver
            # And copy geckodriver to that location
            self.webDriver = webdriver.Firefox(executable_path=os.environ['GECKO_DRIVER'])
        elif "chrome" == driver_type:
            self.webDriver = webdriver.Chrome()
        elif "safari" == driver_type:
            self.webDriver = webdriver.Safari()

    def setupForAB(self):
        print("Test AB supported setup")

    @idata(driver_types)
    def testA(self, driver_type):
        self.create_driver(driver_type)
        self.setupForAB()
        print("Test A ")
        self.assertEquals(2, 2)

    @idata(driver_types)
    def testB(self, driver_type):
        self.create_driver(driver_type)
        self.setupForAB()
        print("Test B")
        self.assertEquals(1, 1)

    @idata(driver_types)
    def testHomePage(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("https://selenium.jacobparr.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)

    def tearDown(self):
        print("After method\n")
        self.webDriver.quit()

    @classmethod
    def tearDownClass(cls):
        print("After class")
