from com.example.selenium.spree.homepage import *

class Pages:
    
    @classmethod
    def openHomePage(cls, test):
        test.webDriver.get("https://spreecommerce-demo.herokuapp.com")
        
        return HomePage(test)

    @classmethod
    def openCartPage(cls, test):
        test.webDriver.get("https://spreecommerce-demo.herokuapp.com/cart")

        from com.example.selenium.spree.cartpage import CartPage
        return CartPage(test)

    @classmethod
    def openProductPage(cls, test):
        test.webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-tote")

        from com.example.selenium.spree.productpage import ProductPage
        return ProductPage(test, "Spree Tote")
