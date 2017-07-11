from com.example.selenium.spree.HomePage import HomePage
from com.example.selenium.spree.SpreePage import BASE_URL


class Pages:
    @classmethod
    def openHomePage(cls, test):
        test.webDriver.get(BASE_URL)
        return HomePage(test)
