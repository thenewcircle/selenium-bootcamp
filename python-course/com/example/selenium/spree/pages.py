class Pages:
    from com.example.selenium.spree.home_page import HomePage
    from com.example.selenium.spree.cart_page import CartPage
    from com.example.selenium.spree.product_page import ProductPage

    @classmethod
    def openHomePage(cls, test) -> HomePage:
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com')

        from com.example.selenium.spree.home_page import HomePage
        return HomePage(test)

    @classmethod
    def openCartPage(cls, test) -> CartPage:
        test.webDriver.get("https://spreecommerce-demo.herokuapp.com/cart")

        from com.example.selenium.spree.cart_page import CartPage
        return CartPage(test)

    @classmethod
    def openProductPage(cls, test) -> ProductPage:
        test.webDriver.get("https://spreecommerce-demo.herokuapp.com/products/spree-tote")

        from com.example.selenium.spree.product_page import ProductPage
        return ProductPage(test)
    
