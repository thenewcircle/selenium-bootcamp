import os
import unittest

from selenium import webdriver
from ddt import ddt, idata

from com.example.selenium.spree.pages import Pages

@ddt
class ShoppingSpreeTests(unittest.TestCase):
    
    # driver_types = ["chrome"]
    driver_types = ["chrome", "firefox", "ie"]

    def create_driver(self, driver_type):
        if "firefox" == driver_type:
            path = os.environ["webdriver_firefox_driver"]
            self.webDriver = webdriver.Firefox(executable_path=path)
        elif "chrome" == driver_type:
            path = os.environ["webdriver_chrome_driver"]
            self.webDriver = webdriver.Chrome(executable_path=path)
        elif "ie" == driver_type:
            path = os.environ["webdriver_ie_driver"]
            self.webDriver = webdriver.Ie(executable_path=path)
        elif "edge" == driver_type:
            path = os.environ["webdriver_edge_driver"]
            self.webDriver = webdriver.Edge(executable_path=path)
        elif "safari" == driver_type:
            self.webDriver = webdriver.Safari()
        elif "grid" == driver_type:
            dc = {
                "browserName": "chrome"
            }
            self.webDriver = webdriver.Remote(
                desired_capabilities=dc,
                command_executor="http://127.0.0.1:4444/wd/hub"
            )

        self.webDriver.implicitly_wait(5)
        self.webDriver.maximize_window()
        # self.webDriver.set_window_size(800, 600)

    def testSearchSpree(self):
        self.create_driver("chrome")
        homePage = Pages.openHomePage(self)
    
        productsPage = homePage.search("Bag")
        productsPage.validateUrl()
        productsPage.validateTitle()

        productsPage.validateSearchText("Bag")
        productsPage.clearSearch()
        productsPage.validateSearchText("")

        homePage = productsPage.clickLogo()
        homePage.validateUrl()
        homePage.validateTitle()

    @idata(driver_types)
    def testGetGoogleUrl(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("http://google.com")
       
        title = self.webDriver.title
        self.assertEquals("Google", title)
        
        url = self.webDriver.current_url
        msg = "Found " + url
        self.assertTrue(url.startswith("https://www.google.com"), msg)

    @idata(driver_types)
    def testNoComments(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("http://www.google.com")
        source = self.webDriver.page_source
        self.assertFalse("<!--" in source)

    @idata(driver_types)
    def testCapabilities(self, driver_type):
        self.create_driver(driver_type)
        capabilities = self.webDriver.capabilities
        
        name = capabilities["browserName"]
        version = capabilities["version"]
        platform = capabilities["platform"]
        js = capabilities["javascriptEnabled"]

        if "chrome" == name:
            self.assertEquals("Windows NT", platform)
            # self.assertEquals("56.0.2924.87", version)
            self.assertTrue(js)
        elif "firefox" == name:
            self.assertEquals("WINDOWS_NT", platform)
            # self.assertEquals("56.0.2924.87", version)
            self.assertTrue(js)
        elif "internet explorer" == name:
            self.assertEquals("WINDOWS", platform)
            # self.assertEquals("56.0.2924.87", version)
            self.assertTrue(js)
        else:
            self.assertEquals("X", name)

    def testDepartmentsCombo2(self):
        self.create_driver("chrome")

        cartPage = Pages.openCartPage(self)
        deptCmb = cartPage.getDepartmentCmb()

    def testDepartmentsCombo(self):
        self.create_driver("chrome")

        homePage = Pages.openHomePage(self)
        deptCmb = homePage.getDepartmentCmb()

        attr = deptCmb.get_attribute("aria-label")
        self.assertEquals("Taxon", attr)

        color = deptCmb.value_of_css_property("background-color")
        # Color is rgba(255, 255, 255, 1)
        if homePage.is_firefox():
            self.assertEquals("rgb(255, 255, 255)", color)
        else:
            self.assertEquals("rgba(255, 255, 255, 1)", color)


        location = deptCmb.location
        # X > 100
        self.assertTrue(location["x"] > 100, "Found: " + str(location))
        # Y < 200
        self.assertTrue(location["y"] < 200, "Found: " + str(location))

        size = deptCmb.size
        # width is between 100 & 200
        self.assertTrue(size["width"] > 100 and size["width"] < 200, "Found: " + str(size))
        # height is between 15 & 25
        self.assertTrue(size["height"] > 15 and size["height"] < 45, "Found: " + str(size))

        tagName = deptCmb.tag_name
        # Is select
        self.assertEquals("select", tagName)

        displayed = deptCmb.is_displayed()
        # is true
        self.assertTrue(displayed)
        
        selected = deptCmb.is_selected()
        # is false
        self.assertFalse(selected)

        enabled = deptCmb.is_enabled()
        # is true
        self.assertTrue(enabled)

        text = deptCmb.text
        # is "All departments\nCategories\nBrand"

        if homePage.is_chrome():
            chromeText = "                                                All departments\n                                                Categories\n                                                Brand\n                                            "
            self.assertEquals(chromeText, text)
        elif homePage.is_ie():
            ieText = "All departments Categories Brand"
            self.assertEquals(ieText, text)
        else:
            self.assertEquals("All departments\nCategories\nBrand", text)

    def testHomePage(self):
        self.create_driver("chrome")

        home_page = Pages.openHomePage(self)
        home_page.validateTitle()
        home_page.validateUrl()

    def testProdPage(self):
        self.create_driver("chrome")

        home_page = Pages.openProdPage(self)
        home_page.validateTitle()
        home_page.validateUrl()

    def testCartPage(self):
        self.create_driver("chrome")

        cart_page = Pages.openCartPage(self)
        cart_page.validateTitle()
        cart_page.validateUrl()

    def testBackAndForth(self):
        self.create_driver("chrome")

        self.webDriver.get("https://selenium.jacobparr.com/products/spree-bag")
        title = self.webDriver.title
        self.assertEquals("Spree Bag - Spree Demo Site", title)

        self.webDriver.get("https://selenium.jacobparr.com/products/spree-tote")
        title = self.webDriver.title
        self.assertEquals("Spree Tote - Spree Demo Site", title)

        self.webDriver.back()
        title = self.webDriver.title
        self.assertEquals("Spree Bag - Spree Demo Site", title)

        self.webDriver.forward()
        title = self.webDriver.title
        self.assertEquals("Spree Tote - Spree Demo Site", title)

        self.webDriver.refresh()
        title = self.webDriver.title
        self.assertEquals("Spree Tote - Spree Demo Site", title)

    def tearDown(self):
        directory = "\\tmp\\test-failures"
        if not os.path.exists(directory):
            os.makedirs(directory)
        file_name = directory + "\\ShoppingSpreeTests-" + self._testMethodName + ".png"
        self.webDriver.get_screenshot_as_file(file_name)
        
        self.webDriver.quit()

    def testShoppingSpree(self):
        self.create_driver("grid")
        home_page = Pages.openHomePage(self)
        
        products_page = home_page.search("Mug")  # search by id

        product_page = products_page.clickProductLnk("Spree Mug")  # byf link text

        url = "https://selenium.jacobparr.com/spree/products/45/product/spree_mug.jpeg?"
        product_page.validateImageSrc(url)  # search by css selector

        product_page.clickThumbnail(1)  # use x-path

        url = "https://selenium.jacobparr.com/spree/products/46/product/spree_mug_back.jpeg?"
        product_page.validateImageSrc(url)  # search by css selector

        product_page.setQuantity(3)  # use by name

        product_page.validateCartLink(0, None)  # use partial link text

        product_page.clickAddToCart()  # use tag name
        product_page.confirmCartResponse("Item Added To Cart")  # use by class name

        product_page.validateCartLink(3, "41.97")

        cart_page = product_page.clickShoppingCart()  # use partial link
        cart_page.clickContinueShopping()  # use whatever
