import os
import unittest

from selenium import webdriver
from ddt import ddt, idata

from com.example.selenium.spree.Pages import Pages


@ddt
class ShoppingSpreeTests2(unittest.TestCase):
    BASE_URL = "https://selenium.jacobparr.com/"
    CHROME = "chrome"
    FIREFOX = "firefox"
    SAFARI = "safari"
    driver_types = [SAFARI]

    @classmethod
    def setUpClass(cls):
        print("Before class")

    def setUp(self):
        print("Before method")

    def create_driver(self, driver_type):
        if self.FIREFOX == driver_type:
            # plug this in the .bash_profile file
            # export GECKO_DRIVER=/Users/$USER/geckodriver
            # And copy geckodriver to that location
            self.webDriver = webdriver.Firefox(executable_path=os.environ['GECKO_DRIVER'])
        elif self.CHROME == driver_type:
            self.webDriver = webdriver.Chrome()
        elif self.SAFARI == driver_type:
            self.webDriver = webdriver.Safari()


    @idata(driver_types)
    def testHomePage(self, driver_type):
        self.create_driver(driver_type)
        home_page = Pages.openHomePage(self)
        home_page.validateTitle()
        home_page.validateUrl()

    @idata(driver_types)
    def testCartPage(self, driver_type):
        self.create_driver(driver_type)
        cart_page = Pages.openCartPage(self)
        cart_page.validateTitle()
        cart_page.validateUrl()

    @idata(driver_types)
    def testProductPage(self, driver_type):
        self.create_driver(driver_type)
        product_page = Pages.openProductPage(self)
        product_page.validateTitle()
        product_page.validateUrl()


    def tearDown(self):
        if hasattr(self, '_outcome'):  # Python 3.4+
            result = self.defaultTestResult()  # these 2 methods have no side effects
            self._feedErrorsToResult(result, self._outcome.errors)
        else:  # Python 3.2 - 3.3 or 2.7
            result = getattr(self, '_outcomeForDoCleanups', self._resultForDoCleanups)
        error = self.list2reason(result.errors)
        failure = self.list2reason(result.failures)
        ok = not error and not failure

        # demo:   report short info immediately (not important)
        if not ok:
            directory = "test-failures"
            if not os.path.exists(directory):
                os.makedirs(directory)

            file_name = directory + "//ShoppingSpreeTests-" + self._testMethodName + ".png"
            self.webDriver.get_screenshot_as_file(file_name)

        self.webDriver.quit()

    def list2reason(self, exc_list):
        if exc_list and exc_list[-1][0] is self:
            return exc_list[-1][1]
