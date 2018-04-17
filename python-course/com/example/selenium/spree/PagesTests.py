from ddt import ddt, idata
from BaseTests import BaseTests

driver_types = ["chrome", "firefox"]


@ddt
class PageTests(BaseTests):
    @idata(driver_types)
    def testGetGoogleUrl(self, driver_type):
        self.create_driver(driver_type)
        self.webDriver.get("http://google.com")
        title = self.webDriver.title
        self.assertEquals("Google", title)
