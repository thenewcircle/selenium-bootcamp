from com.example.selenium.spree.SpreePage import SpreePage

BASE_URL = "https://selenium.jacobparr.com/"


class HomePage(SpreePage):
    def __init__(self, shoppingSpreeTests):
        SpreePage.__init__(self, shoppingSpreeTests)

    def validateTitle(self):
        self._validateTitle("Spree Demo Site")

    def validateUrl(self):
        self._validateUrl(BASE_URL)

    def getDepartmentCmb(self):
        return self.webDriver.find_element_by_id("taxon")

    def getSearchBox(self):
        return self.webDriver.find_element_by_id("keywords")
