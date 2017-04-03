import os
import unittest

from selenium import webdriver

class ShoppingSpreeTests(unittest.TestCase):

    def setUp(self):
        # path = os.environ["webdriver_firefox_driver"]
        # self.webDriver = webdriver.Firefox(executable_path=path)
        #
        # path = os.environ["webdriver_chrome_driver"]
        # self.webDriver = webdriver.Chrome(executable_path=path)
        #
        path = os.environ["webdriver_ie_driver"]
        self.webDriver = webdriver.Ie(executable_path=path)

    def testA(self):
        self.assertTrue(True)

    def testB(self):
        self.assertEquals(1, 1)

    def testHomePage(self):
        self.webDriver.get("https://selenium.jacobparr.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)
    
    def tearDown(self):
        self.webDriver.quit()
