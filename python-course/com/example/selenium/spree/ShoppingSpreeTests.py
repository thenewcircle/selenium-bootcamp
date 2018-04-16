import unittest

from selenium import webdriver
from ddt import ddt, idata

driver_types = ["chrome", "firefox"]


@ddt
class ShoppingSpreeTests(unittest.TestCase):
    def setUp(self):
        print("Before method")

    def create_driver(self, driver_type):
        if "firefox" == driver_type:
            path = "/Users/gilzhaiek/projects/nc/selenium-bootcamp/geckodriver"
            self.webDriver = webdriver.Firefox(executable_path=path)
        elif "chrome" == driver_type:
            path = "/Users/gilzhaiek/projects/nc/selenium-bootcamp/chromedriver"
            self.webDriver = webdriver.Chrome(executable_path=path)

    @idata(driver_types)
    def testGetGoogleUrl(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("http://google.com")
        title = self.webDriver.title
        self.assertEquals("Google", title)
        url = self.webDriver.current_url
        self.assertTrue(url.startswith("https://www.google.com"))
        self.assertEquals(url, "https://www.google.com/?gws_rd=ssl")

    @idata(driver_types)
    def testHomePage(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("https://selenium.jacobparr.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)

    @idata(driver_types)
    def testA(self, driver_type):
        print("Test A")

        self.create_driver(driver_type)
        self.assertTrue(1 == 1)

    @idata(driver_types)
    def testB(self, driver_type):
        print("Test B")

        self.create_driver(driver_type)
        self.assertEquals(1, 1)

    def tearDown(self):
        print("After method\n")
        self.webDriver.quit()
