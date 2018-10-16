import HomePage


class Pages:
    @classmethod
    def openHomePage(cls, test):
        test.webDriver.get('https://spreecommerce-demo.herokuapp.com')
        return HomePage(test)
