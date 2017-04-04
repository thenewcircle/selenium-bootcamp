class Pages:
    from com.example.selenium.spree.home_page import HomePage
    from com.example.selenium.spree.cart_page import CartPage

    @classmethod
    def openHomePage(cls, test) -> HomePage :
        test.webDriver.get("https://selenium.jacobparr.com")

        from com.example.selenium.spree.home_page import HomePage
        return HomePage(test)

    @classmethod
    def openCartPage(cls, test) -> CartPage:
        test.webDriver.get("https://selenium.jacobparr.com/cart")

        from com.example.selenium.spree.cart_page import CartPage
        return CartPage(test)
    
    