class Pages:

    @classmethod
    def openHomePage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com')

        from com.example.selenium.spree.HomePage import HomePage
        return HomePage(test)

    @classmethod
    def openCartPage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com/cart')

        from com.example.selenium.spree.CartPage import CartPage
        return CartPage(test)
    
    @classmethod
    def openProductPage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com/products/spree-tote')

        from com.example.selenium.spree.ProductPage import ProductPage
        return ProductPage(test)
