import unittest

import os
from selenium import webdriver
from ddt import ddt, idata

driver_types = ["chrome"]


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
        self.assertTrue(url.startswith("httpsXX://www.google.com"))
        self.assertEquals(url, "https://www.google.com/?gws_rd=ssl")

    @idata(driver_types)
    def testHomePage(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("https://selenium.jacobparr.com")
        title = self.webDriver.title
        self.assertEquals("Spree Demo Site", title)


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
