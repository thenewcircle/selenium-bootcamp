from com.example.selenium.spree.CartPage import CartPage
from com.example.selenium.spree.HomePage import HomePage
from com.example.selenium.spree.ProductPage import ProductPage


class Pages:

    @classmethod
    def openHomePage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com')
        return HomePage(test)

    @classmethod
    def openCartPage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com/cart')
        return CartPage(test)
    
    
    @classmethod
    def openProductPage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com/products/spree-tote')
        return ProductPage(test)
