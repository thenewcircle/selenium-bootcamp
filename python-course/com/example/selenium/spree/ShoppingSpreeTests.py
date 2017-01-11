import os
import unittest

from selenium import webdriver


class ShoppingSpreeTests(unittest.TestCase):
    def setUp(self):
        # self.webDriver = webdriver.Firefox(executable_path="c:\\tools\\selenium\\geckodriver.exe")
        # self.webDriver = webdriver.Chrome(executable_path="c:\\tools\\selenium\\chromedriver.exe")
        # path = os.environ["webdriver_chrome_driver"]
        # self.webDriver = webdriver.Chrome(executable_path=path)
        path = os.environ["webdriver_ie_driver"]
        self.webDriver = webdriver.Ie(executable_path=path)

    def testHomePage(self):
        self.webDriver.get("https://spreecommerce-demo.herokuapp.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)
    
    def testA(self):
        self.assertTrue(True)
    
    def testB(self):
        self.assertEquals(1, 1)
    
    def tearDown(self):
        self.webDriver.quit()
