from com.example.selenium.spree.CartPage import CartPage
from com.example.selenium.spree.HomePage import HomePage

class Pages:

    @classmethod
    def openHomePage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com')
        return HomePage(test)

    @classmethod
    def openCartPage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com/cart')
        return CartPage(test)