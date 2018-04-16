from ddt import ddt, idata
from BaseTests import BaseTests

driver_types = ["chrome"]


@ddt
class ShoppingSpreeTests(BaseTests):
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

    @idata(driver_types)
    def testCartPage(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("https://selenium.jacobparr.com/cart")
        self.assertEquals(self.webDriver.title, "Shopping Cart - Spree Demo Site")
        self.assertEquals(self.webDriver.current_url, "https://selenium.jacobparr.com/cart")

    @idata(driver_types)
    def testBackAndForth(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("https://selenium.jacobparr.com/products/spree-bag")
        self.assertEquals(self.webDriver.title, "Spree Bag - Spree Demo Site")

        self.webDriver.get("https://selenium.jacobparr.com/products/spree-tote")
        self.assertEquals(self.webDriver.title, "Spree Tote - Spree Demo Site")

        self.webDriver.back()
        self.assertEquals(self.webDriver.title, "Spree Bag - Spree Demo Site")

        self.webDriver.forward()
        self.assertEquals(self.webDriver.title, "Spree Tote - Spree Demo Site")

    @idata(driver_types)
    def testRefresh(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("https://selenium.jacobparr.com/products/spree-bag")
        self.assertEquals(self.webDriver.title, "Spree Bag - Spree Demo Site")
        self.webDriver.refresh()
        self.assertEquals(self.webDriver.title, "Spree Bag - Spree Demo Site")
