from ddt import ddt, idata
from BaseTests import BaseTests
from Pages import Pages

driver_types = ["chrome", "firefox"]


@ddt
class PageTests(BaseTests):
    @idata(driver_types)
    def testLoginPage(self, driver_type):
        self.create_driver(driver_type)
        Pages().open_login_page(self)

    @idata(driver_types)
    def testBadLogin(self, driver_type):
        self.create_driver(driver_type)
        home_page = Pages().open_home_page(self)
        login_page = home_page.navigate_to_login_page()

