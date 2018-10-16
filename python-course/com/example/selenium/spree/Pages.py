from com.example.selenium.spree.pages.CartPage import CartPage
from com.example.selenium.spree.pages.HomePage import HomePage
from com.example.selenium.spree.pages.ProductPage import ProductPage
from com.example.selenium.spree.pages.YahooPage import YahooPage


class Pages:
    @classmethod
    def openHomePage(cls, test):
        test.webDriver.get(HomePage.url)
        return HomePage(test)

    @classmethod
    def openCartPage(cls, test):
        test.webDriver.get(CartPage.url)
        return CartPage(test)


    @classmethod
    def openProductPage(cls, test):
        test.webDriver.get(ProductPage.url)
        return ProductPage(test)

    @classmethod
    def openYahooFinancePage(cls, test):
        test.webDriver.get(YahooPage.url)
        return YahooPage(test)
