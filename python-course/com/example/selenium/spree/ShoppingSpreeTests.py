import os
import unittest

from selenium import webdriver


class ShoppingSpreeTests(unittest.TestCase):

    @classmethod
    def setUpClass(cls):
        print("Before class")

    def setUp(self):
        print("Before method")
        geckoDriverPath = os.environ['GECKO_DRIVER']
        self.webDriverFirefox = webdriver.Firefox(executable_path=geckoDriverPath)
        # self.webDriverChrome = webdriver.Chrome()
        # self.webDriver = webdriver.Safari()

    def setupForAB(self):
        print("Test AB supported setup")

    def testA(self):
        self.setupForAB()
        print("Test A")
        self.assertEquals(2, 2)

    def testB(self):
        self.setupForAB()
        print("Test B")
        self.assertEquals(1, 1)

    def testHomePage(self):
        self.webDriver.get("https://selenium.jacobparr.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)

    def tearDown(self):
        print("After method\n")
        self.webDriver.quit()

    @classmethod
    def tearDownClass(cls):
        print("After class")
