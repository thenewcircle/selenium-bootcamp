import os
import unittest
from enum import Enum

from selenium import webdriver

from com.example.selenium.spree.Pages import Pages
from com.example.selenium.spree.SpreePage import BASE_URL


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
            path = "../../../../geckodriver"
            self.webDriver = webdriver.Firefox(executable_path=path)
        elif DriverType.Chrome == driver_type:
            path = "../../../../chromedriver"
            self.webDriver = webdriver.Chrome(executable_path=path)
        elif DriverType.IE == driver_type:
            # path = os.environ["webdriver_ie_driver"]
            self.webDriver = webdriver.Ie()
        elif DriverType.Edge == driver_type:
            # path = os.environ["webdriver_edge_driver"]
            self.webDriver = webdriver.Edge()
        elif DriverType.Safari == driver_type:
            self.webDriver = webdriver.Safari()

        self.webDriver.implicitly_wait(5)

    def testRefresh(self):
        self.webDriver.get(BASE_URL + "products/spree-bag")
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.refresh()
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

    def testBackAndForth(self):
        self.webDriver.get(BASE_URL + "products/spree-bag")
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.get(BASE_URL + "products/spree-tote")
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)

        self.webDriver.back()
        self.assertEquals("Spree Bag - Spree Demo Site", self.webDriver.title)

        self.webDriver.forward()
        self.assertEquals("Spree Tote - Spree Demo Site", self.webDriver.title)

    def testCapabilities(self):
        capabilities = self.webDriver.capabilities

        name = capabilities["browserName"]

        if "chrome" == name:
            # Add chrome-specific assertion here...
            version = capabilities["version"]
            self.assertEquals("59.0.3071.115", version)

            platform = capabilities["platform"]
            self.assertEquals("Mac OS X", platform)

            js = capabilities["javascriptEnabled"]
            self.assertEquals(True, js)

        elif "firefox" == name:
            version = capabilities["version"]
            self.assertEquals("47.0.1", version)

            platform = capabilities["platform"]
            self.assertEquals("Mac OS X", platform)

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

    def testComments(self):
        self.webDriver.get("http://www.cisco.com/")
        source = self.webDriver.page_source
        # Expect a failure
        self.assertFalse("<!--" in source)

    def testGetGoogleUrl(self):
        self.webDriver.get("http://google.com")
        title = self.webDriver.title
        self.assertEquals("Google", title)
        url = self.webDriver.current_url
        msg = "Found " + url
        self.assertTrue(url.startswith("https://www.google.com/?"), msg)

    def testDepartmentsCombo(self):
        homePage = Pages.openHomePage(self)
        deptCmb = homePage.getDepartmentCmb()
        print("deptCmb text=" + deptCmb.text)
        print("bg color=" + deptCmb.value_of_css_property("background-color"))

    def testHomePage(self):
        home_page = Pages.openHomePage(self)
        home_page.validateTitle()
        home_page.validateUrl()

    def testProductPage(self):
        product_page = Pages.openProductPage(self)
        product_page.validateTitle()
        product_page.validateUrl()

    def testCartPage(self):
        cart_page = Pages.openCartPage(self)
        cart_page.validateTitle()
        cart_page.validateUrl()

    def testA(self):
        self.assertTrue(True)

    def testB(self):
        self.assertEquals(1, 1)

    def tearDown(self):
        # if hasattr(self, '_outcome'):  # Python 3.4+
        #     result = self.defaultTestResult()  # these 2 methods have no side effects
        #     self._feedErrorsToResult(result, self._outcome.errors)
        # else:  # Python 3.2 - 3.3 or 2.7
        #     result = getattr(self, '_outcomeForDoCleanups', self._resultForDoCleanups)
        # error = self.list2reason(result.errors)
        # failure = self.list2reason(result.failures)
        # ok = not error and not failure
        #
        # # demo:   report short info immediately (not important)
        # if not ok:
        #     typ, text = ('ERROR', error) if error else ('FAIL', failure)
        #     msg = [x for x in text.split('\n')[1:] if not x.startswith(' ')][0]
        #     print("\n%s: %s\n     %s" % (typ, self.id(), msg))
        #     directory = "test-failures"
        #     if not os.path.exists(directory):
        #         os.makedirs(directory)
        #
        #     file_name = directory + "//ShoppingSpreeTests-" + self._testMethodName + ".png"
        #     self.webDriver.get_screenshot_as_file(file_name)

        self.webDriver.quit()

    def list2reason(self, exc_list):
        if exc_list and exc_list[-1][0] is self:
            return exc_list[-1][1]
