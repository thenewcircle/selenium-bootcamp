from com.example.selenium.spree.pages.BasePage import BasePage


class HomePage(BasePage):
    title = "Spree Demo Site"
    url = "https://spreecommerce-demo.herokuapp.com/"

    def __init__(self, test):
        BasePage.__init__(self, test, self.title, self.url)
        self.test = test
