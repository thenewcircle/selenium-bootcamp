import unittest

import os
from selenium import webdriver
from ddt import ddt

driver_types = ["chrome"]


@ddt
class BaseTests(unittest.TestCase):
    def setUp(self):
        print("Before method")

    def create_driver(self, driver_type):
        self.drivers_path = "/Users/gilzhaiek/projects/nc/selenium-bootcamp"
        self.firefox_path = os.path.join(self.drivers_path, "geckodriver")
        self.chrome_path = os.path.join(self.drivers_path, "chromedriver")

        if "firefox" == driver_type:
            self.web_driver = webdriver.Firefox(executable_path=self.firefox_path)
        elif "chrome" == driver_type:
            self.web_driver = webdriver.Chrome(executable_path=self.chrome_path)
        self.web_driver.implicitly_wait(5)

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
            self.web_driver.get_screenshot_as_file(file_name)

        self.web_driver.quit()

    def list2reason(self, exc_list):
        if exc_list and exc_list[-1][0] is self:
            return exc_list[-1][1]
