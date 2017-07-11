from com.example.selenium.spree.CartPage import CartPage, CART_PAGE_URL
from com.example.selenium.spree.HomePage import HomePage
from com.example.selenium.spree.ProductPage import ProductPage, PRODUCT_PAGE_URL
from com.example.selenium.spree.SpreePage import BASE_URL


class Pages:
    @classmethod
    def openHomePage(cls, shoppingSpreeTests):
        shoppingSpreeTests.webDriver.get(BASE_URL)
        return HomePage(shoppingSpreeTests)

    @classmethod
    def openCartPage(cls, shoppingSpreeTests):
        shoppingSpreeTests.webDriver.get(CART_PAGE_URL)
        return CartPage(shoppingSpreeTests)

    @classmethod
    def openProductPage(cls, shoppingSpreeTests):
        shoppingSpreeTests.webDriver.get(PRODUCT_PAGE_URL)
        return ProductPage(shoppingSpreeTests)
