import os
import unittest

from selenium import webdriver
from ddt import ddt, idata

import Pages


@ddt
class ShoppingSpreeTests(unittest.TestCase):
    BASE_URL = "https://selenium.jacobparr.com/"
    CHROME = "chrome"
    FIREFOX = "firefox"
    SAFARI = "safari"
    driver_types = [SAFARI, CHROME, FIREFOX]

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
    def testCapabilities(self, driver_type):
        self.create_driver(driver_type)
        capabilities = self.webDriver.capabilities
        name = capabilities["browserName"].lower()

        self.assertEquals(name, driver_type)
        if (driver_type == self.CHROME):
            version = capabilities["version"]
            platform = capabilities["platform"]
            js = capabilities["javascriptEnabled"]
        elif (driver_type == self.FIREFOX):
            version = capabilities["browserVersion"]
            platform = capabilities["platformVersion"]
        elif (driver_type == self.SAFARI):
            version = capabilities["browserVersion"]
            platform = capabilities["platformName"]

    @idata(driver_types)
    def testCartPage(self, driver_type):
        self.create_driver(driver_type)
        # LAB 11.a

    @idata(driver_types)
    def testRefresh(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get(self.BASE_URL + "products/spree-bag")
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.refresh()
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

    @idata(driver_types)
    def testBackAndForth(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get(self.BASE_URL + "products/spree-bag")
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.get(self.BASE_URL + "products/spree-tote")
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)

        self.webDriver.back()
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.forward()
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)


    @idata(driver_types)
    def testHomePage(self, driver_type):
        self.create_driver(driver_type)
        home_page = Pages.openHomePage(self)
        home_page.validateTitle()
        home_page.validateUrl()

    @idata(driver_types)
    def testGetGoogleUrl(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("https://www.google.com")

        source = self.webDriver.page_source
        self.assertTrue("<!--" in source)

        title = self.webDriver.title
        self.assertEquals("Google", title)
        url = self.webDriver.current_url
        self.assertEquals("https://www.google.com/", url)

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
